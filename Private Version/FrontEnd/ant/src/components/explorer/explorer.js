import React, { useEffect } from "react";
import { useLocation } from "react-router-dom";
import Paper from "@material-ui/core/Paper";
import Grid from "@material-ui/core/Grid";
import ExplorerNavigationBar from "./explorer-navigation-bar";
import ExplorerVerticalList from "./explorer-vertical-list";
import CorrectionShower from "./correction-shower";
import Style from "../utils/style";
import Axios from "axios";

export default function Explorer() {
  const style = Style();
  const location = useLocation();

  const [state, setState] = React.useState({
    user: null,
    project: null,
    correction: null
  });
  const [request, setRequest] = React.useState({ source: null, error: null });

  const [users, setUsers] = React.useState([]);
  const [projects, setProjects] = React.useState([]);
  const [corrections, setCorrections] = React.useState([]);

  const deselectUser = () => {
    cancelRequest();
    setState({ user: null, project: null, correction: null });
    setProjects([]);
  };

  const selectUser = user => {
    cancelRequest();
    setState({ user: user, project: null, correction: null });
    getProjects(user);
  };

  const deselectProject = () => {
    cancelRequest();
    setState({ user: state.user, project: null, correction: null });
    setCorrections([]);
  };

  const selectProject = project => {
    cancelRequest();
    setState({ user: state.user, project: project, correction: null });
    getCorrections(project);
  };

  const deselectCorrection = () => {
    cancelRequest();
    setState({ user: state.user, project: state.project, correction: null });
  };

  const selectCorrection = correction => {
    cancelRequest();
    setState({
      user: state.user,
      project: state.project,
      correction: correction
    });
    getCorrection(correction);
  };

  const getUsers = () => {
    let url = "http://localhost:8080/soploon/api/users/";

    const source = Axios.CancelToken.source();

    Axios.get(url, { cancelToken: source.token })
      .then(response => {
        setUsers(response.data);
        setRequest({ source: null, error: null });
      })
      .catch(error => {
        if (Axios.isCancel(error)) {
          setRequest({ source: null, error: null });
        } else {
          setRequest({ source: null, error: error });
        }
      });

    setRequest({ source: source, error: null });
  };

  const getProjects = user => {
    let url =
      "http://localhost:8080/soploon/api/users/" + user.id + "/projects/";
    const source = Axios.CancelToken.source();

    Axios.get(url, { cancelToken: source.token })
      .then(response => {
        setProjects(response.data);
        setRequest({ source: null, error: null });
      })
      .catch(error => {
        if (Axios.isCancel(error)) {
          setRequest({ source: null, error: null });
        } else {
          setRequest({ source: null, error: error });
        }
      });

    setRequest({ source: source, error: null });
  };

  const getCorrections = project => {
    let url =
      "http://localhost:8080/soploon/api/users/" +
      project.userId +
      "/projects/" +
      project.id +
      "/corrections/";
    const source = Axios.CancelToken.source();

    Axios.get(url, { cancelToken: source.token })
      .then(response => {
        setCorrections(response.data);
        setRequest({ source: null, error: null });
      })
      .catch(error => {
        if (Axios.isCancel(error)) {
          setRequest({ source: null, error: null });
        } else {
          setRequest({ source: null, error: error });
        }
      });

    setRequest({ source: source, error: null });
  };

  const getCorrection = correction => {
    const { userId, projectId, date } = correction;
    let url =
      "http://localhost:8080/soploon/api/users/" +
      userId +
      "/projects/" +
      projectId +
      "/corrections/" +
      date;

    const source = Axios.CancelToken.source();

    Axios.get(url, { cancelToken: source.token })
      .then(response => {
        setState({
          user: state.user,
          project: state.project,
          correction: response.data
        });
        setRequest({ source: null, error: null });
      })
      .catch(error => {
        if (Axios.isCancel(error)) {
          setRequest({ source: null, error: null });
        } else {
          setRequest({ source: null, error: error });
        }
      });

    setRequest({ source: source, error: null });
  };

  const cancelRequest = () => {
    if (request.source !== null) {
      request.source.cancel();
      setRequest({ source: null, error: null });
    }
  };

  const navigationBarActions = () => {
    return {
      selectInit: deselectUser,
      selectUser: deselectProject,
      selectProject: deselectCorrection
    };
  };

  const navigationBarData = () => {
    return state;
  };

  const verticalListData = () => {
    return {
      currentUser: state.user,
      currentProject: state.project,
      currentCorrection: state.correction,
      users,
      projects,
      corrections
    };
  };

  const verticalListActions = () => {
    return { selectUser, selectProject, selectCorrection };
  };

  useEffect(() => {
    //ACA DEJO EL OBJETO ERROR! (location.state)
    //ACOMODAR EL AXIOS CANCEL TOKEN CUANDO SE TERMINE ESTA CLASE
    console.log(location.state);
    getUsers();
  }, [location]);

  return (
    <div>
      <Grid container spacing={3}>
        <Grid item xs={12}>
          <Paper className={style.paper}>
            <ExplorerNavigationBar
              style={style}
              data={navigationBarData()}
              actions={navigationBarActions()}
            />
          </Paper>
        </Grid>
      </Grid>
      {state.correction == null ? (
        <Grid container spacing={3}>
          <Grid item xs={12} sm={12} md={4}>
            <Paper className={style.paper}>
              <ExplorerVerticalList
                loading={request.source !== null}
                data={verticalListData()}
                actions={verticalListActions()}
              />
            </Paper>
          </Grid>
        </Grid>
      ) : (
        <CorrectionShower style={style} correction={state.correction} />
      )}
    </div>
  );
}
