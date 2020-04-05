import React, { useEffect } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Login from "./components/login/login";
import auth from "./components/utils/Auth.js";
import PrivateRouter from "./components/utils/PrivateRouter.js";
import Barra from "./Barra";
import { Result } from "antd";

export default function App() {
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
