# Use a lightweight web server as the base image
FROM nginx:alpine

# Copy the contents of Calculator directory into the web server's document root
COPY Calculator/ /usr/share/nginx/html

# Expose port 80 (default HTTP port)
EXPOSE 80
