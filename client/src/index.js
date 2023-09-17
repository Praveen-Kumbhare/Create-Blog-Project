import React from "react";
import ReactDOM from "react-dom/client";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { ContextProvider } from "./context/Context";
import { SearchProvider } from "./context/SearchContext";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <BrowserRouter>
    <ContextProvider>
      <SearchProvider>
        <App />
      </SearchProvider>
    </ContextProvider>
  </BrowserRouter>
);
