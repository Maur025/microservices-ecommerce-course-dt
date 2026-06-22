# --- ETAPA 1: BUILD (La construccion) ---
# Puede ser llamado computadora A, solo vivira mientras construye la aplicacion, luego se destruye

# Imagen de linux que tiene maven y java 25 para compilar el proyecto
FROM maven:3.9.16-eclipse-temurin-25-alpine AS builder
# crea la mesa de trabajo y entra en la carpeta app dentro del contenedor
WORKDIR /app
# copiar desde nuestra pc hacia el contenedor, primer puento mi pc segundo punto el contenedor
# copia el contenido actual de la carpeta . es la actual
# lo copia en la carpeta donde se encuentra el contenedor . en este caso estamos dentro de /app
COPY . .
# corre el comando de construccion evitando ejecutar los tests
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUNTIME (El producto final) ---
# Podriamos llamarlo computadora B, que se mantendra viva ejecutando la aplicacion

# imagen de linux que contiene jre que es mas pequeño que jdk, para una ejecucion mas ligera, eficiente y segura
FROM eclipse-temurin:25-jre-alpine
WORKDIR /app
# aqui copiamos/extraemos el jar desde el paso anterior que nombramos builder y que vive en su propio contenedor
# lo copia desde target algun archivo con la extensio jar, en este caso sera nuestra unica aplicacion
# lo copia y lo renombra como app.jar en el contenedor de runtime
# solo extraemos el jar necesario
COPY --from=builder /app/target/*.jar app.jar
# sirve para documentar el puerto donde se debe ejecutar internamente
EXPOSE 8080

# configura que debe hacer el contenedor cuando le dan play o ejecutan el comando de iniciar, es diferente de run
ENTRYPOINT ["java", "-jar", "app.jar"]