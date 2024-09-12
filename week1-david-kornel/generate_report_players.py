import sqlite3

# Connect to the SQLite database
conn = sqlite3.connect('redteam.db')
cur = conn.cursor()

# Define SQL queries
queries = {
    'players': """
        SELECT id, jersey, picture_link, player_name, position, height, weight 
        FROM players;
    """,
    'player_averages': """
        SELECT 
            (SELECT COUNT(1) || '/' || (SELECT MAX(fordulo) FROM gameStatistics) FROM gameStatistics WHERE id = ?) AS games_played,
            minutes, totalMakes, totalAttempts, totalPercentage,
            threePointMakes, threePointAttempts, threePointPercentage,
            faulMakes, faulAttempts, faulPercentage, totalRebound,
            assists, turnovers, steals, blocks
        FROM playerStats 
        WHERE id = ? AND type = 'AVERAGE';
    """,
    'recent_games': """
        SELECT 
            fordulo, minutes, totalMakes, totalAttempts, totalPercentage,
            threePointMakes, threePointAttempts, threePointPercentage,
            faulMakes, faulAttempts, faulPercentage, totalRebound,
            assists, turnovers, steals, blocks
        FROM gameStatistics 
        WHERE id = ?
        ORDER BY fordulo DESC
        LIMIT 3;
    """
}

try:
    # Fetch all players
    cur.execute(queries['players'])
    players = cur.fetchall()

    # Start generating the HTML
    html = """
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <style>
            body { font-family: Arial, sans-serif; margin: 0; padding: 0; }
            .team-report { width: 100%; max-width: 1200px; margin: 0 auto; }
            .player-container { margin-bottom: 40px; padding: 20px; border: 1px solid #ddd; border-radius: 8px; background-color: #f9f9f9; }
            .player-image { width: 154px; height: 220px; float: left; margin-right: 20px; }
            .player-details { overflow: hidden; }
            .player-header { font-weight: bold; font-size: 20px; margin-bottom: 10px; border-bottom: 2px solid #333; padding-bottom: 10px; }
            .section { margin-bottom: 20px; padding-bottom: 10px; border-bottom: 2px solid #333; }
            table { width: 100%; border-collapse: collapse; }
            th, td { text-align: center; padding: 10px; border: 1px solid #ddd; font-size: 14px; }
            th { background-color: #f4f4f4; }
            th:nth-child(1), td:nth-child(1) { width: 100px; }
            th:nth-child(2), td:nth-child(2) { width: 60px; }
            th:nth-child(3), td:nth-child(3) { width: 80px; }
            th:nth-child(4), td:nth-child(4) { width: 80px; }
            th:nth-child(5), td:nth-child(5) { width: 80px; }
            th:nth-child(6), td:nth-child(6) { width: 80px; }
            th:nth-child(7), td:nth-child(7) { width: 80px; }
            th:nth-child(8), td:nth-child(8) { width: 80px; }
            th:nth-child(9), td:nth-child(9) { width: 80px; }
            th:nth-child(10), td:nth-child(10) { width: 80px; }
            th:nth-child(11), td:nth-child(11) { width: 80px; }
            th:nth-child(12), td:nth-child(12) { width: 80px; }
            th:nth-child(13), td:nth-child(13) { width: 80px; }
            th:nth-child(14), td:nth-child(14) { width: 80px; }
            th:nth-child(15), td:nth-child(15) { width: 80px; }
            th:nth-child(16), td:nth-child(16) { width: 80px; }
            th:nth-child(17), td:nth-child(17) { width: 80px; }
            textarea { width: 100%; padding: 10px; margin-top: 10px; }
            ul { list-style-type: none; padding: 0; margin: 0; }
            li { margin-bottom: 10px; display: flex; align-items: center; font-weight: bold; color: #ff5733; }
            .remove-icon { cursor: pointer; color: red; margin-left: 10px; }
        </style>
    </head>
    <body>
        <div class="team-report">
    """

    # Loop through each player and generate HTML
    for idx, player in enumerate(players, start=1):
        player_id, jersey, picture_link, player_name, position, height, weight = player

        # Fetch player averages
        cur.execute(queries['player_averages'], (player_id, player_id))
        averages = cur.fetchone()

        # Check if averages are None and handle accordingly
        if averages:
            games_played, minutes, total_makes, total_attempts, total_percentage, \
            three_point_makes, three_point_attempts, three_point_percentage, \
            faul_makes, faul_attempts, faul_percentage, total_rebound, assists, \
            turnovers, steals, blocks = averages

            # Convert percentages to float and handle None values
            try:
                total_percentage = float(total_percentage) if total_percentage else 0.0
                three_point_percentage = float(three_point_percentage) if three_point_percentage else 0.0
                faul_percentage = float(faul_percentage) if faul_percentage else 0.0
            except ValueError:
                total_percentage = three_point_percentage = faul_percentage = 0.0
        else:
            games_played = minutes = total_makes = total_attempts = total_percentage = \
            three_point_makes = three_point_attempts = three_point_percentage = \
            faul_makes = faul_attempts = faul_percentage = total_rebound = assists = \
            turnovers = steals = blocks = "N/A"
            total_percentage = three_point_percentage = faul_percentage = 0.0

        player_html = f"""
        <div class="player-container">
            <img src="{picture_link}" alt="{player_name}" class="player-image">
            <div class="player-details">
                <div class="player-header">#{jersey} {player_name} {position} {height} cm {weight} kg</div>
                <div class="section">
                    <strong>Season Averages:</strong>
                    <table>
                        <tr>
                            <th>GP</th><th>MIN</th><th>FGM</th><th>FGA</th><th>FG%</th><th>3PM</th><th>3PA</th><th>3P%</th><th>FTM</th><th>FTA</th><th>FT%</th><th>REB</th><th>AST</th><th>TO</th><th>STL</th><th>BLK</th>
                        </tr>
                        <tr>
                            <td>{games_played}</td><td>{minutes}</td><td>{total_makes}</td><td>{total_attempts}</td><td>{total_percentage:.1f}</td><td>{three_point_makes}</td><td>{three_point_attempts}</td><td>{three_point_percentage:.1f}</td><td>{faul_makes}</td><td>{faul_attempts}</td><td>{faul_percentage:.1f}</td><td>{total_rebound}</td><td>{assists}</td><td>{turnovers}</td><td>{steals}</td><td>{blocks}</td>
                        </tr>
                    </table>
                </div>
                <div class="section">
                    <strong>Last 3 Games:</strong>
                    <table>
                        <tr>
                            <th>Fordulo</th><th>MIN</th><th>FGM</th><th>FGA</th><th>FG%</th><th>3PM</th><th>3PA</th><th>3P%</th><th>FTM</th><th>FTA</th><th>FT%</th><th>REB</th><th>AST</th><th>TO</th><th>STL</th><th>BLK</th>
                        </tr>
        """

        # Fetch recent game stats
        cur.execute(queries['recent_games'], (player_id,))
        recent_games = cur.fetchall()

        # Fill in recent games data
        for game in recent_games:
            fordulo, minutes, total_makes, total_attempts, total_percentage, \
            three_point_makes, three_point_attempts, three_point_percentage, \
            faul_makes, faul_attempts, faul_percentage, total_rebound, assists, \
            turnovers, steals, blocks = game

            player_html += f"""
            <tr>
                <td>{int(fordulo)}</td><td>{int(minutes)}</td><td>{int(total_makes)}</td><td>{int(total_attempts)}</td><td>{float(total_percentage):.1f}</td><td>{int(three_point_makes)}</td><td>{int(three_point_attempts)}</td><td>{float(three_point_percentage):.1f}</td><td>{int(faul_makes)}</td><td>{int(faul_attempts)}</td><td>{float(faul_percentage):.1f}</td><td>{int(total_rebound)}</td><td>{int(assists)}</td><td>{int(turnovers)}</td><td>{int(steals)}</td><td>{int(blocks)}</td>
            </tr>
            """

        player_html += f"""
                    </table>
                </div>
                <div class="section">
                    <strong>Additional Details:</strong>
                    <ul id="details-list-{idx}" class="details-list"></ul>
                    <textarea id="additional-info-{idx}" rows="4" placeholder="Add details here..."></textarea>
                </div>
            </div>
        </div>
        """

        # Append player HTML to the main HTML
        html += player_html

    # Close the HTML tags and add JavaScript
    html += """
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', function() {
                function loadDetails(id) {
                    const list = document.getElementById('details-list-' + id);
                    const details = JSON.parse(localStorage.getItem('details-' + id)) || [];
                    list.innerHTML = '';
                    details.forEach((item, index) => {
                        const li = document.createElement('li');
                        li.textContent = item;
                        const removeIcon = document.createElement('span');
                        removeIcon.textContent = '−';  // Unicode for the minus sign
                        removeIcon.classList.add('remove-icon');
                        removeIcon.onclick = function() {
                            details.splice(index, 1);
                            localStorage.setItem('details-' + id, JSON.stringify(details));
                            loadDetails(id);
                        };
                        li.appendChild(removeIcon);
                        list.appendChild(li);
                    });
                }

                function saveDetails(id) {
                    const textarea = document.getElementById('additional-info-' + id);
                    const list = document.getElementById('details-list-' + id);
                    const details = JSON.parse(localStorage.getItem('details-' + id)) || [];
                    if (textarea.value.trim() !== '') {
                        details.push(textarea.value.trim());
                        localStorage.setItem('details-' + id, JSON.stringify(details));
                        textarea.value = '';
                        loadDetails(id);
                    }
                }

                document.querySelectorAll('textarea').forEach(textarea => {
                    textarea.addEventListener('keydown', function(event) {
                        if (event.key === 'Enter') {
                            event.preventDefault();
                            const id = this.id.split('-').pop();
                            saveDetails(id);
                        }
                    });
                });

                document.querySelectorAll('textarea').forEach(textarea => {
                    const id = textarea.id.split('-').pop();
                    loadDetails(id);
                });
            });
        </script>
    </body>
    </html>
    """

    # Write the HTML to a file with UTF-8 encoding
    with open('team_report_players.html', 'w', encoding='utf-8') as file:
        file.write(html)

except sqlite3.Error as e:
    print(f"SQLite error: {e}")

finally:
    # Close the database connection
    conn.close()

