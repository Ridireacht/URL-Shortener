# Get Nginx
FROM nginx:alpine

# Copy Nginx configuration file
COPY nginx.conf /etc/nginx/conf.d/default.conf

# Copy all static files
COPY . /usr/share/nginx/html