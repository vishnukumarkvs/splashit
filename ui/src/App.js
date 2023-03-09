import "./App.css";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getConfig } from "./store/actions/configActions";
import {Routes, Route} from 'react-router-dom';
import Home from "./pages/Home";
import NotFound from "./pages/NotFound";
import Upload from "./pages/Upload";

function App() {
  const timestamp = useSelector((state) => state.config.timestamp);
  const config = useSelector((state) => state.config);
  const dispatch = useDispatch();
  const fetchConfig = () => {
    if (
      !timestamp ||
      new Date().getTime() - timestamp.getTime() > 1000 * 60 * 60 * 3
    ) {
      dispatch(getConfig());
    }
  };
  useEffect(() => {
    fetchConfig();
  }, []);
  useEffect(() => {
    console.log(config);
  }, [config]);
  return (
    <Routes>
      <Route path={"/"} element={<Home/>}/>
      <Route path={"/upload"} element={<Upload/>}/>
      <Route path="*" element={<NotFound/>}/>
    </Routes>
  );
}

export default App;
