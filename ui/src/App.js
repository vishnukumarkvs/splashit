import logo from "./logo.svg";
import "./App.css";
import { useEffect } from "react";
import { useDispatch, useSelector } from "react-redux";
import { getConfig } from "./store/actions/configActions";

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
    <div className="App">
      <header className="App-header">
        <img src={logo} className="App-logo" alt="logo" />
        <p>
          Edit <code>src/App.js</code> and save to reload.
        </p>
        <a
          className="App-link"
          href="https://reactjs.org"
          target="_blank"
          rel="noopener noreferrer"
        >
          Learn React
        </a>
      </header>
    </div>
  );
}

export default App;
