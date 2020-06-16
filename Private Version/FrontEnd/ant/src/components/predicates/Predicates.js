import React, { useEffect } from "react";
import Axios from "axios";
import TablePredicates from "./TablePredicates";
import EditPredicate from "./EditPredicate";
import { Switch, Route, useRouteMatch, withRouter } from "react-router-dom";

function Predicates({ history }) {
  let { path, url } = useRouteMatch();
  const [oldData, setOldData] = React.useState({
    id: "",
    version: "",
    name: "",
    description: "",
    code: "",
    activated: ""
  });

  const handleClickOpen = (event, rowData) => {
    if (rowData !== undefined) {
      setInputs(rowData.selected);
      setOldData(rowData.selected);
    }
    history.push(`${url}/form`);
  };

  const handleActivatedChange = (event, predicate) => {
    var predicadoSeleccionado = predicate.selected;
    var newValue = !predicadoSeleccionado.activated;
    predicadoSeleccionado.activated = newValue;

    let url =
      "https://si.isistan.unicen.edu.ar/soploon/api/predicates/" +
      predicadoSeleccionado.id;
    Axios.put(url, predicadoSeleccionado)
      .then(response => {
        const data = [...entries.data];
        const position = buscarIndice(data, predicate.selected);
        data[position].versions.forEach(el => {
          if (el.version !== predicadoSeleccionado.version) {
            el.activated = false;
          } else {
            el.activated = response.data.activated;
          }
        });
        setEntries({ data });
      })
      .catch(response => {});
  };
  const handleVersionChange = (event, predicate) => {
    const data = [...entries.data];
    const position = data.indexOf(predicate);
    var selectedVersion = event.target.value - 1;
    data[position].selected = data[position].versions[selectedVersion];
    setEntries({ data });
  };

  const handleClose = () => {
    handleCleaner();
    history.goBack();
  };

  const buscarIndice = (predicateList, predicate) => {
    if (predicateList.length === 0) return -1;

    var index = 0;

    while (
      index < predicateList.length &&
      predicate.id !== predicateList[index].selected.id
    )
      index++;

    if (index >= predicateList.length) return -1;

    return index;
  };

  const guardarEditar = () => {
    let url = "https://si.isistan.unicen.edu.ar/soploon/api/predicates/" + inputs.id;
    Axios.post(url, inputs)
      .then(response => {
        const data = [...entries.data];
        const position = buscarIndice(data, oldData);
        data[position].selected = response.data;
        data[position].versions.forEach(el => {
          el.activated = false;
        });
        data[position].versions.push(response.data);
        setEntries({ data });
        handleClose();
      })
      .catch(response => {});
  };

  const guardarNuevoPredicado = () => {
    let url = "https://si.isistan.unicen.edu.ar/soploon/api/predicates/";
    Axios.post(url, inputs)
      .then(response => {
        var predicateObject = {};
        predicateObject.versions = [];
        predicateObject.versions.push(response.data);
        predicateObject.selected = response.data;
        const data = [...entries.data, predicateObject];
        setEntries({ data });
        handleClose();
      })
      .catch(response => {});
  };

  const guardar = oldData => {
    if (inputs.id !== undefined) guardarEditar();
    else {
      guardarNuevoPredicado();
    }
  };

  //Regla
  const [entries, setEntries] = React.useState({
    data: []
  });

  const initState = {
    name: "",
    description: "",
    code: "",
    activated: "true"
  };
  //Entradas formulario
  const [inputs, setInputs] = React.useState({
    name: "",
    description: "",
    code: "",
    activated: "true"
  });

  const handleChange = e => {
    const { name, value } = e.target;
    setInputs({ ...inputs, [name]: value });
  };

  const handleCleaner = e => {
    setInputs(initState);
    setOldData(initState);
  };
  const processData = data => {
    var predicates = {};

    data.forEach(predicateVersion => {
      if (predicates[predicateVersion.id] === undefined) {
        var predicate = {};
        predicate.versions = [];
        predicate.versions.push(predicateVersion);
        predicates[predicateVersion.id] = predicate;
      } else {
        predicates[predicateVersion.id].versions.push(predicateVersion);
      }
    });

    Object.entries(predicates).forEach(keyvalue => {
      var predicate = keyvalue[1];

      var index = 0;
      while (
        predicate.selected === undefined &&
        index < predicate.versions.length
      ) {
        if (predicate.versions[index].activated)
          predicate.selected = predicate.versions[index];
        index++;
      }

      if (predicate.selected === undefined)
        predicate.selected = predicate.versions[predicate.versions.length - 1];
    });

    return predicates;
  };

  useEffect(() => {
    Axios.get("https://si.isistan.unicen.edu.ar/soploon/api/predicates/")
      .then(response => {
        let data = [];
        var result = processData(response.data);
        Object.entries(result).forEach(keyvalue => {
          data.push(keyvalue[1]);
        });
        setEntries({ data: data });
      })
      .catch(function (error) {
        console.log(error);
      });
  }, []);

  return (
    <Switch>
      <Route exact path={path}>
        <TablePredicates
          {...{
            handleClickOpen,
            handleVersionChange,
            handleActivatedChange,
            entries
          }}
        />
      </Route>
      <Route exact path={`${path}/form`}>
        <EditPredicate {...{ handleClose, guardar, handleChange, inputs }} />
      </Route>
    </Switch>
  );
}

export default withRouter(Predicates);
