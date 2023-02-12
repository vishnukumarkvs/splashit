const express = require("express");
const jwt = require("jsonwebtoken");
const { createProxyMiddleware } = require("http-proxy-middleware");
const { default: axios } = require("axios");
require("dotenv").config();

const app = express();

// const coreServiceUrl = "http://localhost:8002";
// const uploadServiceUrl = "http://localhost:8003";
// const commentSericeUrl = "http://localhost:8001";
// const jwtSecret = "vishnu";

const coreServiceUrl = process.env.CORE_SERVICE_URL;
const uploadServiceUrl = process.env.UPLOAD_SERVICE_URL;
const commentSericeUrl = process.env.COMMENT_SERVICE_URL;
const jwtSecret = process.env.JWT_SECRET;

app.use(async (req, res, next) => {
  if (req.path === "/login" || req.path === "/register") {
    next();
    return;
  }
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
    target: coreServiceUrl,
    changeOrigin: true,
    onProxyReq: (proxyReq, req) => {
      console.log("proxying");
      console.log(req.body);
    },
  })
);

app.use(
  "/upload",
  createProxyMiddleware({
    target: uploadServiceUrl,
    changeOrigin: true,
    onProxyReq: (proxyReq, req) => {
      console.log("proxying");
      console.log(req.body);
    },
  })
);

app.use(
  "/comments",
  createProxyMiddleware({
    target: commentSericeUrl,
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
    const { data } = await axios.post(
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
  try {
    let config = {
      headers: {
        "Content-Type": "application/json",
      },
    };
    console.log(req.body);
    const { data } = await axios.post(
      coreServiceUrl + "/auth/login",
      req.body,
      config
    );
    // user is authenticated, create JWT token
    const token = jwt.sign({ userId: data.id }, jwtSecret);
    res.json({ token, user: data });
  } catch (err) {
    res.status(401).json({ error: "Invalid email or password" });
    console.log(err);
  }
});

app.get("/health", (req, res) => {
  res.status(200).send({ status: "UP" });
});

app.listen(5000, () => {
  console.log(`Listening to port: 5000`);
});
