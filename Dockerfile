 FROM sbtscala/scala-sbt:eclipse-temurin-jammy-22_36_1.10.0_3.4.2

 # Update und Installation der benötigten Pakete
 ENV DISPLAY=host.docker.internal:0.0
 
 RUN apt-get update && \
     apt-get install -y \
     libxrender1 \
     libxtst6 \
     libxi6 \
     libgl1-mesa-glx libgtk-3-0 openjfx libgl1-mesa-dri libgl1-mesa-dev libcanberra-gtk-module libcanberra-gtk3-module default-jdk
 
 # Arbeitsverzeichnis im Container
 WORKDIR /app
 
 # Kopiere den aktuellen Inhalt des Projekts in das Arbeitsverzeichnis
 COPY . /app
 
 # Kompiliere das Projekt während des Builds (optional, falls benötigt)
 RUN sbt compile
 
 # Setze das Standardkommando, um das Projekt auszuführen
 CMD ["sbt", "run"]