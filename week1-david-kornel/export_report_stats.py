import pdfkit

# Path to wkhtmltopdf executable
path_to_wkhtmltopdf = '/usr/bin/wkhtmltopdf'  # Adjust this if needed

# Configure pdfkit to use wkhtmltopdf
config = pdfkit.configuration(wkhtmltopdf=path_to_wkhtmltopdf)

# Path to the HTML file
html_file = 'team_report.html'

# Path to save the output PDF file
output_pdf = 'team_report.pdf'

# Convert the HTML file to PDF
pdfkit.from_file(html_file, output_pdf, configuration=config)

print(f"PDF saved as {output_pdf}")

