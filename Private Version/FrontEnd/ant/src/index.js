import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import Sider from './components/Sider'
import RegistrationForm from './components/RegistrationForm'
import * as serviceWorker from './serviceWorker';
import {BrowserRouter} from 'react-router-dom';



ReactDOM.render(<App/>, document.getElementById('root'));

serviceWorker.unregister();
