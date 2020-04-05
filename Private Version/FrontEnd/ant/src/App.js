import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Login from "./components/login/login";
import PrivateRouter from "./components/utils/PrivateRouter.js";
import Barra from "./Barra";
import Axios from "axios";

export default function App() {
  const cargarToken = () => {
    console.log("seteando token");
    let user = JSON.parse(localStorage.getItem("auth"));
    if (user != null && user.authenticated) {
      Axios.defaults.headers.common["Authorization"] = "Bearer " + user.token;
      console.log(user.token);
    }
  };

  cargarToken();

  return (
    <div className="app-routes">
      <BrowserRouter>
        <Switch>
          <Route exact path="/login" component={Login} />
          <PrivateRouter path="/" component={Barra} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}
