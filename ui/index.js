const express = require("express");
const path = require("path");
const app = express();
require("dotenv").config();

app.use(express.json());
app.use(express.static(path.join(__dirname, "build")));

app.get("/config", (req, res) => {
  res.json({
    coreMicroserviceUrl: process.env.CORE_MICROSERVICE_URL,
    authMicroserviceUrl: process.env.AUTH_MICROSERVICE_URL,
    uploadMicroserviceUrl: process.env.UPLOAD_MICROSERVICE_URL,
    commentMicroserviceUrl: process.env.COMMENT_MICROSERVICE_URL,
  });
});

app.get("*", (req, res) => {
  res.sendFile(path.resolve(__dirname, "build", "index.html"));
});

app.listen(3000, () => {
  console.log("UI Microservice running...");
});
