#!/bin/zsh

cd /home/ubuntu/retargeting-branding-affiliate-system
export DOCKER_CLIENT_TIMEOUT=300
export COMPOSE_HTTP_TIMEOUT=300    
sudo docker-compose -f docker-compose.yml up -d --build