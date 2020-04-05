import React, { Component } from "react";
import { Redirect, Route } from "react-router-dom";

class PrivateRouter extends Component {
  render() {
    const { user, component, ...rest } = this.props;
    return user.isAuthenticated() ? (
      <Route component={component} {...rest} />
    ) : (
      <Redirect
        to={{ pathname: "/login", state: { from: this.props.location } }}
      />
    );
  }
}

export default PrivateRouter;
