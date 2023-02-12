import { createReducer } from "@reduxjs/toolkit";

const initialState = {
  name: null,
  email: null,
  id: null,
  created_date: null,
  token: null,
};

const userReducer = createReducer(initialState, (builder) => {
  builder.addCase("SET_USER", (state, action) => {
    state.name = action.payload?.name;
    state.email = action.payload?.email;
    state.id = action.payload?.id;
    state.created_date = action.payload?.created_date;
    state.token = action.payload?.token;
  });
});

export default userReducer;
