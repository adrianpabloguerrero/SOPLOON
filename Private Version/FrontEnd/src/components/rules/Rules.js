import React, { useEffect } from "react";
import EditRule from "./EditRule.js";
import Axios from "axios";
import TableRules from "./TableRules";
import { Switch, Route, useRouteMatch, withRouter } from "react-router-dom";

function Rules({ history }) {
  let { path, url } = useRouteMatch();

  const [oldData, setOldData] = React.useState({
    id: "",
    version: "",
    name: "",
    description: "",
    link: "",
    query: "",
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

  const handleActivatedChange = (event, rule) => {
    var reglaSeleccionada = rule.selected;
    var newValue = !reglaSeleccionada.activated;
    reglaSeleccionada.activated = newValue;

    let url = "https://si.isistan.unicen.edu.ar/soploon/api/rules/" + reglaSeleccionada.id;
    Axios.put(url, reglaSeleccionada)
      .then(response => {
        const data = [...entries.data];
        const position = buscarIndice(data, rule.selected);
        data[position].versions.forEach(el => {
          if (el.version !== reglaSeleccionada.version) {
            el.activated = false;
          } else {
            el.activated = response.data.activated;
          }
        });
        setEntries({ data });
      })
      .catch(response => {});
  };
  const handleVersionChange = (event, rule) => {
    const data = [...entries.data];
    const position = data.indexOf(rule);
    var selectedVersion = event.target.value - 1;
    data[position].selected = data[position].versions[selectedVersion];
    setEntries({ data });
  };

  const handleClose = () => {
    handleCleaner();
    history.goBack();
  };

  const buscarIndice = (ruleList, rule) => {
    if (ruleList.length === 0) return -1;

    var index = 0;

    while (index < ruleList.length && rule.id !== ruleList[index].selected.id)
      index++;

    if (index >= ruleList.length) return -1;

    return index;
  };

  const guardarEditar = () => {
    let url = "https://si.isistan.unicen.edu.ar/soploon/api/rules/" + inputs.id;
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

  const guardarNuevaRegla = () => {
    let url = "https://si.isistan.unicen.edu.ar/soploon/api/rules/";
    Axios.post(url, inputs)
      .then(response => {
        var ruleObject = {};
        ruleObject.versions = [];
        ruleObject.versions.push(response.data);
        ruleObject.selected = response.data;
        const data = [...entries.data, ruleObject];
        setEntries({ data });
        handleClose();
      })
      .catch(response => {});
  };

  const guardar = oldData => {
    if (inputs.id !== undefined) guardarEditar();
    else {
      guardarNuevaRegla();
    }
  };

  //Regla
  const [entries, setEntries] = React.useState({
    data: []
  });

  const initState = {
    name: "",
    description: "",
    link: "",
    query: "",
    code: "",
    activated: "true"
  };
  //Entradas formulario
  const [inputs, setInputs] = React.useState({
    name: "",
    description: "",
    link: "",
    query: "",
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
    var rules = {};

    data.forEach(ruleVersion => {
      if (rules[ruleVersion.id] === undefined) {
        var rule = {};
        rule.versions = [];
        rule.versions.push(ruleVersion);
        rules[ruleVersion.id] = rule;
      } else {
        rules[ruleVersion.id].versions.push(ruleVersion);
      }
    });

    Object.entries(rules).forEach(keyvalue => {
      var rule = keyvalue[1];

      var index = 0;
      while (rule.selected === undefined && index < rule.versions.length) {
        if (rule.versions[index].activated)
          rule.selected = rule.versions[index];
        index++;
      }

      if (rule.selected === undefined)
        rule.selected = rule.versions[rule.versions.length - 1];
    });

    return rules;
  };

  useEffect(() => {
    let source = Axios.CancelToken.source();

    Axios.get("https://si.isistan.unicen.edu.ar/soploon/api/rules/", {
      cancelToken: source.token
    })
      .then(response => {
        let data = [];
        var result = processData(response.data);
        Object.entries(result).forEach(keyvalue => {
          data.push(keyvalue[1]);
        });
        setEntries({ data: data });
      })
      .catch(function (thrown) {});
    return () => source.cancel();
  }, []);
  return (
    <Switch>
      <Route exact path={path}>
        <TableRules
          {...{
            handleClickOpen,
            handleVersionChange,
            handleActivatedChange,
            entries
          }}
        />
      </Route>
      <Route exact path={`${path}/form`}>
        <EditRule {...{ handleClose, guardar, handleChange, inputs }} />
      </Route>
    </Switch>
  );
}

export default withRouter(Rules);
