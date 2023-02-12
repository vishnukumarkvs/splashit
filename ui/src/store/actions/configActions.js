import axios from "axios";

export const getConfig = async () => {
  const res = await axios.get("/config");
  const config = { ...res.data, timestamp: new Date() };
  return {
    type: "SET_CONFIG",
    payload: config,
  };
};
