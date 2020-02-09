import React, { useEffect } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import Paper from '@material-ui/core/Paper';
import Grid from '@material-ui/core/Grid';
import Typography from '@material-ui/core/Typography';
import TreeView from "@material-ui/lab/TreeView";
import TreeItem from "@material-ui/lab/TreeItem";
import SyntaxHighlighter from 'react-syntax-highlighter';
import { docco } from 'react-syntax-highlighter/dist/esm/styles/hljs';
import PackageIcon from '@material-ui/icons/BorderAll';
import ExpandIcon from '@material-ui/icons/Add';
import CollapseIcon from '@material-ui/icons/Remove';
import FileIcon from '@material-ui/icons/Description';
import ExplorerNavigationBar from './explorer-navigation-bar';
import ExplorerVerticalList from './explorer-vertical-list';

import Axios from 'axios';

const useStyles = makeStyles(theme => ({
  root: {
    flexGrow: 1,
  },
  paper: {
    padding: theme.spacing(2),
    textAlign: 'center',
    color: theme.palette.text.secondary,
  },
  label: {
    fontWeight: 'inherit',
    color: 'inherit',
  },
  labelRoot: {
    display: 'flex',
    alignItems: 'center',
    padding: theme.spacing(0.5, 0),
  },
  labelIcon: {
    marginRight: theme.spacing(1),
	fontSize: 'small',
  },
  labelText: {
    fontWeight: 'inherit',
    flexGrow: 1,
  }
}));

export default function Explorer() {

	const classes = useStyles();
	
	const [state,setState] = React.useState({ user: null, project: null, correction: null});

	const [users, setUsers] = React.useState([]);
	const [projects, setProjects] = React.useState([]);
	const [corrections, setCorrections] = React.useState([]);
	const [sources, setSources] = React.useState([]);
	const [sourceFile, setSourceFile] = React.useState({});

	
	const deselectUser = () => {
		setState({user: null, project: null, correction: null});
	}
	
	const selectUser = (user) => {
		setState({user: user, project: null, correction: null});
		getProjects(user);
	}
	
	const deselectProject = () => {
		setState({user: state.user, project: null, correction: null});
	}

	const selectProject = (project) => {
		setState({user: state.user, project: project, correction: null});
		getCorrections(project);
	}
	
	const deselectCorrection = () => {
		setState({user: state.user, project: state.project, correction: null});
	}
	
	const selectCorrection = (correction) => {
		setState({user: state.user, project: state.project, correction: correction});
		structureSource(correction);
	}
		
	const showSource = () => {
		return (state.correction != null);
	};
		
	const getUsers = () => {
		let url = 'http://localhost:8080/soploon/api/users/';
		Axios.get(url)
			.then(response => {
				setUsers(response.data);
			})
			.catch(response => {
			});
	};

	const getProjects = (user) => {
		let url = 'http://localhost:8080/soploon/api/users/' + user.id + '/projects/';
		Axios.get(url)
			.then(response => {
				setProjects(response.data);
			})
			.catch(response => {
			});
	};

	const getCorrections = (project) => {
		let url = 'http://localhost:8080/soploon/api/users/' + project.userId + '/projects/' + project.id + '/corrections/';
		Axios.get(url)
			.then(response => {
				setCorrections(response.data);
			})
			.catch(response => {
			});
	};
			
	const structureSource = (correction) => {
		let folders = {childrens: []};
		
		correction.code.forEach(sourceFile => {
			var paths = sourceFile.path.split("/");
			paths = paths.slice(1,paths.length);
			
			var partialPath = "";
			
			var currentFolder = folders;
			paths.forEach(path => {
				var current = null;
				partialPath = partialPath + "/" + path;
				currentFolder.childrens.forEach(node => {
					if (node.name === path)
						current = node;
				});
				
				if (current == null) {
					current = {};
					current.name = path;
					current.id = partialPath;
					current.childrens = [];
					currentFolder.childrens.push(current);
				}

				currentFolder = current;
				
			});
			
			currentFolder.source = sourceFile.code;
	
		});
		
		setSources(folders.childrens);
	}
	
	const showSourceFile = () => {
		return sourceFile.name !== undefined;
	}
	
	const getTreeItemsFromData = treeItems => {
	  return treeItems.map(treeItemData => {
		let children = undefined;
		if (treeItemData.childrens && treeItemData.childrens.length > 0) {
		  children = getTreeItemsFromData(treeItemData.childrens);
		}
		if (treeItemData.childrens.length > 0) {
			return (
			  <TreeItem
				key={treeItemData.id}
				nodeId={treeItemData.id}
				label={
					<div className={classes.labelRoot}>
					  <PackageIcon color="inherit" className={classes.labelIcon} />
					  <Typography variant="body2" className={classes.labelText}>
						{treeItemData.name}
					  </Typography>
					</div>
				}
				children={children}
			  />
			);
		} else {
			return (
			  <TreeItem onClick={ () => { setSourceFile(treeItemData); } }
				key={treeItemData.id}
				nodeId={treeItemData.id}
				label={
					<div className={classes.labelRoot}>
					  <FileIcon color="inherit" className={classes.labelIcon} />
					  <Typography variant="body2" className={classes.labelText}>
						{treeItemData.name}
					  </Typography>
					</div>
				}
				children={children}
			/>)
		}
	  });
	};
	
	const DataTreeView = ({ treeItems }) => {
	  return (
		<TreeView
		  defaultExpandIcon={<ExpandIcon  className={classes.labelIcon}/>}
		  defaultCollapseIcon={<CollapseIcon  className={classes.labelIcon}/>}>
		  {getTreeItemsFromData(treeItems)}
		</TreeView>
	  );
	};

	const navigationBarActions = () => {
		return {'selectInit': deselectUser, 'selectUser': deselectProject, 'selectProject': deselectCorrection};
	}
	
	const navigationBarData = () => {
		return state;
	}

	const verticalListData = () => {
		return {'currentUser': state.user, 'currentProject': state.project, 'currentCorrection': state.correction, users, projects, corrections};
	}
	
	const verticalListActions = () => {
		return {selectUser, selectProject, selectCorrection};
	}

	useEffect(() => { getUsers(); }, []);

	return (
		<div>
			<Grid container spacing={3}>
				<Grid item xs={12}>
					<Paper className={classes.paper}>
					  <ExplorerNavigationBar data={navigationBarData()} actions={navigationBarActions()}/>
					</Paper>
				</Grid>
				<Grid item xs={3}>
					<Paper className={classes.paper}>
						<ExplorerVerticalList data={verticalListData()} actions={verticalListActions()}/>
						<div hidden={!showSource()} style={{'textAlign': 'left'}}>
							<DataTreeView treeItems={sources} />
						</div>
					</Paper>
				</Grid>
				<Grid hidden={!showSourceFile()} item xs={9}>
					<Paper className={classes.paper}>
						<div style={{'textAlign': 'left'}}>
							{ sourceFile.name }
							<SyntaxHighlighter language="java" style={docco}>
							  { sourceFile.source !== undefined ? sourceFile.source.replace(/\t/g,"   ") : ''}
							</SyntaxHighlighter>
						</div>
					</Paper>
				</Grid>
			</Grid>
		</div>
	);

}
