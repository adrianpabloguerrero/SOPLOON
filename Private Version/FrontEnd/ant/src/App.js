import React from 'react';
import './App.css';
import { Router, Route, browserHistory } from 'react-router';
import Barra from './Barra.js';

const App = () => (
  <div id= "container" className="App">
    <Barra/>
  </div>
);

export default App;
