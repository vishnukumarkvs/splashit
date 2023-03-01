import { combineReducers } from "@reduxjs/toolkit";
import configReducer from "./configReducer";
import userReducer from "./userReducer";

const reducer = combineReducers({
  user: userReducer,
  config: configReducer,
});

export default reducer;
