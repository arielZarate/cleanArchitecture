#!/bin/bash

# Descargar Maven 3.9.9
cd /tmp
wget -q https://dlcdn.apache.org/maven/maven-3/3.9.9/binaries/apache-maven-3.9.9-bin.tar.gz

# Extraer
sudo tar -xzf apache-maven-3.9.9-bin.tar.gz -C /opt/

# Respaldar y actualizar enlace
sudo rm -f /usr/share/maven
sudo ln -s /opt/apache-maven-3.9.9 /usr/share/maven

# Verificar
mvn --version
