import axios from "axios";
import {
  TRANSACTIONS_LOADING,
  TRANSACTIONS_ERROR,
  TRANSACTIONS_SET,
  CHANGE_FILE,
} from "./constants";

const httpClient = axios.create({
  baseURL: "http://localhost:8080",
});

export const init = () => async (dispatch) => {
  try {
    dispatch({
      type: TRANSACTIONS_LOADING,
    });

    const transactionsList = await httpClient.get("/transactions");

    dispatch({
      type: TRANSACTIONS_SET,
      payload: transactionsList.data,
    });
  } catch (error) {
    dispatch({
      type: TRANSACTIONS_ERROR,
      payload: error,
    });
  }
};

export const changeFile = (file) => (dispatch) => {
  dispatch({ type: CHANGE_FILE, payload: file });
};

export const submit = (file) => async (dispatch) => {
  let formData = new FormData();
  formData.append("file", file);

  try {
    dispatch({ type: TRANSACTIONS_LOADING });

    await httpClient.post("/transactions", formData, {
      headers: {
        "content-type": "multipart/form-data",
      },
    });

    const transactionsList = await httpClient.get("/transactions");

    dispatch({
      type: TRANSACTIONS_SET,
      payload: transactionsList.data,
    });
  } catch (error) {
    dispatch({ type: TRANSACTIONS_ERROR });
  }
};
