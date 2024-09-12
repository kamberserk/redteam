import sqlite3

# Connect to the SQLite database
conn = sqlite3.connect('redteam.db')
conn.text_factory = str  # Ensure text factory is set to handle Unicode
cur = conn.cursor()

# Queries to fetch data
queries = {
    'starters': """
    SELECT p.jersey, p.player_name, CAST(a.points AS INTEGER) AS points
    FROM gameStatistics a
    JOIN players p ON a.id = p.id 
    WHERE a.fordulo = (SELECT MAX(fordulo) FROM gameStatistics) 
    AND a.starter = 1
    ORDER BY points DESC;
    """,
    'bench': """
    SELECT p.jersey, p.player_name, CAST(a.points AS INTEGER) AS points
    FROM gameStatistics a
    JOIN players p ON a.id = p.id 
    WHERE a.fordulo = (SELECT MAX(fordulo) FROM gameStatistics) 
    AND a.starter = 0
    ORDER BY points DESC;
    """,
    'three_pointers': """
    SELECT p.jersey, p.player_name, s.ThreePointPercentage AS '3P%', s.ThreePointAttempts AS '3PA'
    FROM PlayerStats s
    JOIN players p ON s.id = p.id
    WHERE s.type = 'AVERAGE'
    ORDER BY s.ThreePointPercentage DESC, s.ThreePointAttempts DESC;
    """,
    'free_throws': """
    SELECT p.jersey, p.player_name, s.FaulPercentage AS 'FT%',
        (SELECT sx.FaulAttempts
         FROM PlayerStats sx 
         WHERE sx.type = 'TOTAL' AND p.id = sx.id) AS 'Σ(FTA)'
    FROM PlayerStats s
    JOIN players p ON s.id = p.id 
    WHERE s.type = 'AVERAGE'
    ORDER BY s.FaulPercentage ASC, 'Σ(FTA)' DESC;
    """,
    'inactive_players': """
    SELECT p.jersey, p.player_name
    FROM players p
    WHERE p.id NOT IN (SELECT id FROM gameStatistics WHERE fordulo = (SELECT MAX(fordulo) FROM gameStatistics))
    ORDER BY p.jersey;
    """,
    'key_stats': """
    SELECT rank, type, value FROM instatteam_key_stats_rankings;
    """,
    'offensive_stats': """
    SELECT rank, type, value FROM instatteam_offensive_stats_rankings;
    """,
    'defensive_stats': """
    SELECT rank, type, value FROM instatteam_defensive_stats_rankings;
    """
}

data = {}
for key, query in queries.items():
    cur.execute(query)
    data[key] = cur.fetchall()

# Helper function to get the rank suffix
def get_rank_suffix(rank):
    if 10 <= rank % 100 <= 20:
        suffix = 'th'
    else:
        suffix = {1: 'st', 2: 'nd', 3: 'rd'}.get(rank % 10, 'th')
    return suffix

# Start of HTML content
html = """
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Basketball Team Report</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f9f9f9;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        .header {
            text-align: center;
            padding: 20px;
            background-color: #333;
            color: #fff;
            width: 100%;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }
        .container {
            width: 100%;
            max-width: 1200px;
            margin: 20px;
            box-sizing: border-box;
        }
        .row {
            display: flex;
            flex-wrap: wrap;
            gap: 10px;
            margin-bottom: 20px;
        }
        .column {
            flex: 1;
            min-width: 200px;
        }
        .section {
            border: 1px solid #000;
            padding: 10px;
            box-shadow: 2px 2px 8px rgba(0, 0, 0, 0.1);
            background-color: #fff;
            box-sizing: border-box;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            table-layout: auto;
        }
        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            font-size: 14px;
            overflow: hidden;
            text-overflow: ellipsis;
            box-sizing: border-box;
            cursor: pointer; /* Indicate that cells are clickable */
        }
        th {
            background-color: #f4f4f4;
        }
        th#narrow {
            width: 60px;
        }
        th#points, th#attempts {
            width: 80px;
        }
        th#name {
            width: 200px;
        }
        .highlight-green {
            background-color: lightgreen;
        }
        .highlight-red {
            background-color: lightcoral;
        }
        .highlight-default {
            background-color: #fff;
        }
    </style>
    <script>
        // Function to handle color change
        function toggleColor(cell) {
            const currentClass = cell.className;
            if (currentClass === 'highlight-green') {
                cell.className = 'highlight-red';
            } else if (currentClass === 'highlight-red') {
                cell.className = 'highlight-default';
            } else {
                cell.className = 'highlight-green';
            }
            saveColors();
        }

        // Function to save colors to local storage
        function saveColors() {
            const colorData = {};
            document.querySelectorAll('td[data-row-id]').forEach(cell => {
                const rowId = cell.getAttribute('data-row-id');
                colorData[rowId] = cell.className;
            });
            localStorage.setItem('playerColors', JSON.stringify(colorData));
        }

        // Function to load colors from local storage
        function loadColors() {
            const colorData = JSON.parse(localStorage.getItem('playerColors') || '{}');
            document.querySelectorAll('td[data-row-id]').forEach(cell => {
                const rowId = cell.getAttribute('data-row-id');
                if (colorData[rowId]) {
                    cell.className = colorData[rowId];
                }
            });
        }

        // Load colors on page load
        window.onload = loadColors;
    </script>
</head>
<body>

    <div class="header">
        <h1>Team Report</h1>
    </div>

    <div class="container">
        <!-- First row for Starting Five, Inactive Players, Bench, 3-Point Shooters, and FT Shooters -->
        <div class="row">
            <!-- Column 1: Starting Five and Inactive Players stacked -->
            <div class="column">
                <div id="starters-section" class="section">
                    <h2>Last Game Starting Five</h2>
                    <table>
                        <tr><th id="narrow">#</th><th id="name">Player</th><th id="points">Points</th></tr>
"""

# Function to generate unique data-row-id
def generate_data_row_id(table_name, jersey, column):
    return f"{table_name}-{jersey}-{column}"

# Populate the table with the starters' data
for starter in data['starters']:
    jersey, player_name, points = starter
    jersey_str = str(jersey)
    data_row_id_num = generate_data_row_id('starters', jersey_str, 'jersey')
    data_row_id_name = generate_data_row_id('starters', jersey_str, 'name')
    data_row_id_points = generate_data_row_id('starters', jersey_str, 'points')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_num}" onclick="toggleColor(this)">{jersey}</td>
                            <td data-row-id="{data_row_id_name}" onclick="toggleColor(this)">{player_name}</td>
                            <td data-row-id="{data_row_id_points}" onclick="toggleColor(this)">{points}</td>
                        </tr>
    """

# Close the HTML tags for the starters section
html += """
                    </table>
                </div>
                
                <div id="inactive-players-section" class="section">
                    <h2>Inactive Players</h2>
                    <table>
                        <tr><th id="narrow">#</th><th id="name">Player</th></tr>
"""

# Populate the table with the inactive players' data
for inactive in data['inactive_players']:
    jersey, player_name = inactive
    jersey_str = str(jersey)
    data_row_id_num = generate_data_row_id('inactive', jersey_str, 'jersey')
    data_row_id_name = generate_data_row_id('inactive', jersey_str, 'name')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_num}" onclick="toggleColor(this)">{jersey}</td>
                            <td data-row-id="{data_row_id_name}" onclick="toggleColor(this)">{player_name}</td>
                        </tr>
    """

# Close the HTML tags for the inactive players section
html += """
                    </table>
                </div>
            </div>

            <!-- Column 2: Bench -->
            <div class="column">
                <div id="bench-section" class="section">
                    <h2>Bench</h2>
                    <table>
                        <tr><th id="narrow">#</th><th id="name">Player</th><th id="points">Points</th></tr>
"""

# Populate the table with the bench players' data
for bench_player in data['bench']:
    jersey, player_name, points = bench_player
    jersey_str = str(jersey)
    data_row_id_num = generate_data_row_id('bench', jersey_str, 'jersey')
    data_row_id_name = generate_data_row_id('bench', jersey_str, 'name')
    data_row_id_points = generate_data_row_id('bench', jersey_str, 'points')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_num}" onclick="toggleColor(this)">{jersey}</td>
                            <td data-row-id="{data_row_id_name}" onclick="toggleColor(this)">{player_name}</td>
                            <td data-row-id="{data_row_id_points}" onclick="toggleColor(this)">{points}</td>
                        </tr>
    """

# Close the HTML tags for the bench section
html += """
                    </table>
                </div>
            </div>

            <!-- Column 3: 3-Point Shooters -->
            <div class="column">
                <div id="three-pointers-section" class="section">
                    <h2>3-Point Shooters</h2>
                    <table>
                        <tr><th id="narrow">#</th><th id="name">Player</th><th>3P%</th><th id="attempts">3PA</th></tr>
"""

# Populate the table with the 3-point shooters' data
for shooter in data['three_pointers']:
    jersey, player_name, three_p_percentage, three_p_attempts = shooter
    jersey_str = str(jersey)
    data_row_id_num = generate_data_row_id('three_pointers', jersey_str, 'jersey')
    data_row_id_name = generate_data_row_id('three_pointers', jersey_str, 'name')
    data_row_id_3p = generate_data_row_id('three_pointers', jersey_str, '3p')
    data_row_id_3pa = generate_data_row_id('three_pointers', jersey_str, '3pa')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_num}" onclick="toggleColor(this)">{jersey}</td>
                            <td data-row-id="{data_row_id_name}" onclick="toggleColor(this)">{player_name}</td>
                            <td data-row-id="{data_row_id_3p}" onclick="toggleColor(this)">{three_p_percentage}</td>
                            <td data-row-id="{data_row_id_3pa}" onclick="toggleColor(this)">{three_p_attempts}</td>
                        </tr>
    """

# Close the HTML tags for the 3-point shooters section
html += """
                    </table>
                </div>
            </div>

            <!-- Column 4: Free Throw Shooters -->
            <div class="column">
                <div id="free-throws-section" class="section">
                    <h2>Free Throw Shooters</h2>
                    <table>
                        <tr><th id="narrow">#</th><th id="name">Player</th><th>FT%</th><th id="attempts">Σ(FTA)</th></tr>
"""

# Populate the table with the free throw shooters' data
for ft_shooter in data['free_throws']:
    jersey, player_name, ft_percentage, ft_attempts = ft_shooter
    jersey_str = str(jersey)
    data_row_id_num = generate_data_row_id('free_throws', jersey_str, 'jersey')
    data_row_id_name = generate_data_row_id('free_throws', jersey_str, 'name')
    data_row_id_ft = generate_data_row_id('free_throws', jersey_str, 'ft')
    data_row_id_fta = generate_data_row_id('free_throws', jersey_str, 'fta')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_num}" onclick="toggleColor(this)">{jersey}</td>
                            <td data-row-id="{data_row_id_name}" onclick="toggleColor(this)">{player_name}</td>
                            <td data-row-id="{data_row_id_ft}" onclick="toggleColor(this)">{ft_percentage}</td>
                            <td data-row-id="{data_row_id_fta}" onclick="toggleColor(this)">{ft_attempts}</td>
                        </tr>
    """

# Close the HTML tags for the free throw shooters section
html += """
                    </table>
                </div>
            </div>
        </div>

        <!-- Second row for Key Stats, Offensive Stats, and Defensive Stats -->
        <div class="row">
            <!-- Column 1: Key Stats -->
            <div class="column">
                <div id="key-stats-section" class="section">
                    <h2>Key Stats</h2>
                    <table>
                        <tr><th>Rank</th><th>Type</th><th>Value</th></tr>
"""

# Populate the table with key stats data
for stat in data['key_stats']:
    rank, type_, value = stat
    suffix = get_rank_suffix(rank)
    data_row_id_rank = generate_data_row_id('key_stats', rank, 'rank')
    data_row_id_type = generate_data_row_id('key_stats', rank, 'type')
    data_row_id_value = generate_data_row_id('key_stats', rank, 'value')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_rank}" onclick="toggleColor(this)">{rank}{suffix}</td>
                            <td data-row-id="{data_row_id_type}" onclick="toggleColor(this)">{type_}</td>
                            <td data-row-id="{data_row_id_value}" onclick="toggleColor(this)">{value}</td>
                        </tr>
    """

# Close the HTML tags for the key stats section
html += """
                    </table>
                </div>
            </div>

            <!-- Column 2: Offensive Stats -->
            <div class="column">
                <div id="offensive-stats-section" class="section">
                    <h2>Offensive Stats</h2>
                    <table>
                        <tr><th>Rank</th><th>Type</th><th>Value</th></tr>
"""

# Populate the table with offensive stats data
for stat in data['offensive_stats']:
    rank, type_, value = stat
    suffix = get_rank_suffix(rank)
    data_row_id_rank = generate_data_row_id('offensive_stats', rank, 'rank')
    data_row_id_type = generate_data_row_id('offensive_stats', rank, 'type')
    data_row_id_value = generate_data_row_id('offensive_stats', rank, 'value')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_rank}" onclick="toggleColor(this)">{rank}{suffix}</td>
                            <td data-row-id="{data_row_id_type}" onclick="toggleColor(this)">{type_}</td>
                            <td data-row-id="{data_row_id_value}" onclick="toggleColor(this)">{value}</td>
                        </tr>
    """

# Close the HTML tags for the offensive stats section
html += """
                    </table>
                </div>
            </div>

            <!-- Column 3: Defensive Stats -->
            <div class="column">
                <div id="defensive-stats-section" class="section">
                    <h2>Defensive Stats</h2>
                    <table>
                        <tr><th>Rank</th><th>Type</th><th>Value</th></tr>
"""

# Populate the table with defensive stats data
for stat in data['defensive_stats']:
    rank, type_, value = stat
    suffix = get_rank_suffix(rank)
    data_row_id_rank = generate_data_row_id('defensive_stats', rank, 'rank')
    data_row_id_type = generate_data_row_id('defensive_stats', rank, 'type')
    data_row_id_value = generate_data_row_id('defensive_stats', rank, 'value')
    html += f"""
                        <tr>
                            <td data-row-id="{data_row_id_rank}" onclick="toggleColor(this)">{rank}{suffix}</td>
                            <td data-row-id="{data_row_id_type}" onclick="toggleColor(this)">{type_}</td>
                            <td data-row-id="{data_row_id_value}" onclick="toggleColor(this)">{value}</td>
                        </tr>
    """

# Close the HTML tags for the defensive stats section
html += """
                    </table>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
"""

# Save HTML to file
with open('team_report_stats.html', 'w', encoding='utf-8') as file:
    file.write(html)

# Close database connection
conn.close()

