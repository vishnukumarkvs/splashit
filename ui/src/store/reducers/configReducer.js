import { createReducer } from "@reduxjs/toolkit";

const initialState = {
  authMicroserviceUrl: null,
  uploadMicroserviceUrl: null,
  coreMicroserviceUrl: null,
  commentMicroserviceUrl: null,
  timestamp: null,
};

const configReducer = createReducer(initialState, (builder) => {
  builder.addCase("SET_CONFIG", (state, action) => {
    state.authMicroserviceUrl = action.payload?.authMicroserviceUrl;
    state.uploadMicroserviceUrl = action.payload?.uploadMicroserviceUrl;
    state.coreMicroserviceUrl = action.payload?.coreMicroserviceUrl;
    state.commentMicroserviceUrl = action.payload?.commentMicroserviceUrl;
    state.timestamp = action.payload.timestamp;
  });
  builder.addCase("RESET_CONFIG", (state) => {
    state.authMicroserviceUrl = null;
    state.uploadMicroserviceUrl = null;
    state.coreMicroserviceUrl = null;
    state.commentMicroserviceUrl = null;
  });
});

export default configReducer;
