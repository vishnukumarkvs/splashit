const express = require("express");
const jwt = require("jsonwebtoken");
const { expressjwt: expressJwt } = require("express-jwt");
const { createProxyMiddleware } = require("http-proxy-middleware");
const { default: axios } = require("axios");

const app = express();

const coreServiceUrl = "http://localhost:8080";
const jwtSecret = "vishnu";

app.use(async (req, res, next) => {
  console.log("working");
  const token = req.header("Authorization");
  if (!token) {
    res.status(401).json({ mssg: "Token not found" });
  }
  try {
    const verfiy = await jwt.verify(token, jwtSecret);
    req.headers["user"] = JSON.stringify(verfiy);
    //console.log(req.user);
    next();
  } catch (err) {
    console.log(err);
    res.status(401).json({ mssg: "Invalid token" });
  }
});

app.use(
  "/core",
  createProxyMiddleware({
    target: "http://localhost:8080",
    changeOrigin: true,
    onProxyReq: (proxyReq, req) => {
      console.log("proxying");
      console.log(req.body);
    },
  })
);

app.use(express.json());

app.post("/register", async (req, res) => {
  try {
    const { data, status } = await axios.post(
      coreServiceUrl + "/auth/register",
      req.body.user
    );
    if (data) {
      // user is authenticated, create JWT token
      const token = jwt.sign({ userId: data.id }, jwtSecret);
      res.json({ token, user: data });
    } else {
      res.status(401).json({ error: "Invalid email or password" });
    }
  } catch (err) {
    console.log(err);
    res.status(err.response.status).json(err.response.data);
  }
});

app.post("/login", async (req, res) => {
  const { data, status } = await axios.post(
    coreServiceUrl + "/login",
    req.body.user
  );
  if (data) {
    // user is authenticated, create JWT token
    const token = jwt.sign({ userId: data.id }, jwtSecret);
    res.json({ token, user: data });
  } else {
    res.status(401).json({ error: "Invalid email or password" });
  }
});

app.listen(5000, () => {
  console.log(`Listening to port: 5000`);
});
