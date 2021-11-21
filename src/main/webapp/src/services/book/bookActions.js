import * as BT from "./bookTypes";
import axios from "axios";

export const saveBook = (book) => {
  return (dispatch) => {
    dispatch({
      type: BT.SAVE_BOOK_REQUEST,
    });
    axios
      .post("http://localhost:8081/rest/books/save", book)
      .then((response) => {
        console.log(response);
        dispatch(bookSuccess(response.data));
      })
      .catch((error) => {
        console.log(error);
        dispatch(bookFailure(error));
      });
  };
};

export const fetchBook = (bookId) => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_BOOK_REQUEST,
    });
    axios
      .get("http://localhost:8081/rest/books/" + bookId)
      .then((response) => {
        dispatch(bookSuccess(response.data));
      })
      .catch((error) => {
        dispatch(bookFailure(error));
      });
  };
};

export const updateBook = (book) => {
  return (dispatch) => {
    dispatch({
      type: BT.UPDATE_BOOK_REQUEST,
    });
    axios
      .put("http://localhost:8081/rest/books/update", book)
      .then((response) => {
        dispatch(bookSuccess(response.data));
      })
      .catch((error) => {
        dispatch(bookFailure(error));
      });
  };
};

export const deleteBook = (bookId) => {
  return (dispatch) => {
    dispatch({
      type: BT.DELETE_BOOK_REQUEST,
    });
    axios
      .delete("http://localhost:8081/rest/books/" + bookId)
      .then((response) => {
        dispatch(bookSuccess(response.data));
      })
      .catch((error) => {
        dispatch(bookFailure(error));
      });
  };
};

const bookSuccess = (book) => {
  return {
    type: BT.BOOK_SUCCESS,
    payload: book,
  };
};

const bookFailure = (error) => {
  return {
    type: BT.BOOK_FAILURE,
    payload: error,
  };
};

export const fetchLanguages = () => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_LANGUAGES_REQUEST,
    });
    axios
      .get("http://localhost:8081/rest/books/languages")
      .then((response) => {
        dispatch({
          type: BT.LANGUAGES_SUCCESS,
          payload: response.data,
        });
      })
      .catch((error) => {
        dispatch({
          type: BT.LANGUAGES_FAILURE,
          payload: error,
        });
      });
  };
};

export const fetchTypes = () => {
  return (dispatch) => {
    dispatch({
      type: BT.FETCH_TYPES_REQUEST,
    });
    axios
      .get("http://localhost:8081/rest/books/types")
      .then((response) => {
        dispatch({
          type: BT.TYPES_SUCCESS,
          payload: response.data,
        });
      })
      .catch((error) => {
        dispatch({
          type: BT.TYPES_FAILURE,
          payload: error,
        });
      });
  };
};
