import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Login from "./components/login/login";
import Barra from "./Barra";

export default function App() {
  return (
    <div className="app-routes">
      <BrowserRouter>
        <Switch>
          <Route path="/login" component={Login} />
          <Route path="/index" component={Barra} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}
