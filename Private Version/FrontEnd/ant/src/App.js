import React, { useEffect } from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Login from "./components/login/login";
import auth from "./components/utils/Auth.js";
import PrivateRouter from "./components/utils/PrivateRouter.js";
import Barra from "./Barra";

export default function App() {
  useEffect(() => {
    localStorage.clear();
  }, []);

  return (
    <div className="app-routes">
      <BrowserRouter>
        <Switch>
          <Route exact path="/login" component={Login} />
          <PrivateRouter user={auth} path="/" component={Barra} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}
