import React, { Component } from "react";
import { Redirect, Route } from "react-router-dom";

class PrivateRouter extends Component {
  render() {
    const { component, ...rest } = this.props;
    let user = JSON.parse(localStorage.getItem("auth"));
    return user != null && user.authenticated ? (
      <Route component={component} {...rest} />
    ) : (
      <Redirect
        to={{ pathname: "/login", state: { from: this.props.location } }}
      />
    );
  }
}

export default PrivateRouter;
