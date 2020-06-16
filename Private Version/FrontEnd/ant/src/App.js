import React from "react";
import { BrowserRouter, Switch, Route } from "react-router-dom";
import Login from "./components/login/login";
import PrivateRouter from "./components/utils/PrivateRouter.js";
import Barra from "./Barra";
import Axios from "axios";
import auth from "./components/utils/Auth.js";

export default function App() {
  const cargarToken = () => {
    Axios.interceptors.response.use((response) => {
	   return response
	}, function (error) {
	   const originalRequest = error.config;
	   if ((error.response.status === 401 || error.response.status === 403) && !originalRequest._retry) {
		   originalRequest._retry = true;
		   var oldAuth = JSON.parse(localStorage.getItem("auth"));
		   return Axios.post("https://si.isistan.unicen.edu.ar/soploon/api/authentication/", JSON.stringify(oldAuth.credentials), { headers: {'Content-Type': 'application/json' }} )
		   .then(response => {
			   if (response.status === 200) {
					Axios.defaults.headers.common["Authorization"] = "Bearer " + response.data.token;
					auth.login(oldAuth.credentials, response.data);
					localStorage.setItem("auth", JSON.stringify(auth));
					originalRequest.headers.Authorization = "Bearer " + response.data.token;
					return Axios(originalRequest);
			   }
		   });
	   }
	});
	
    let user = JSON.parse(localStorage.getItem("auth"));
    if (user != null && user.authenticated) {
      Axios.defaults.headers.common["Authorization"] = "Bearer " + user.token;
      console.log(user.token);
    }
  };

  cargarToken();

  return (
    <div className="app-routes">
      <BrowserRouter basename={'/soploon/admin'}>
        <Switch>
          <Route exact path="/login" component={Login} />
          <PrivateRouter path="/" component={Barra} />
        </Switch>
      </BrowserRouter>
    </div>
  );
}
