import React from "react";
import "./App.css";
import Barra from "./Barra.js";

export const Context = React.createContext();

const App = () => (
  <div id="container" className="App">
    <Barra />
  </div>
);

export default App;
