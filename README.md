# Simple Web Application
## That is my web application
Frontend based on Vue.js and backend on Springboot

## Discription
The apllication is SPA with two views - authorization page and main page

### Login page
Authorization page used Spring Security with JWT Token

### Main page
Main page contains simple form, graph and results table. User choise coordinates X, Y, radius R and send this data on server. The server checks if the point is in the chart area.
The server answer contains score hit (true/false) and additional data. User also can click on graph to send data on server.
The table with the results contains individual for each registered user.

### About
Project was builded as monolith application. You can find all frontend code in "src/frontend" directory. Backend located in "com.example.demo" folder.
