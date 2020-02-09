import React from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TreeView from "@material-ui/lab/TreeView";
import TreeItem from "@material-ui/lab/TreeItem";
import PackageIcon from '@material-ui/icons/BorderAll';
import ExpandIcon from '@material-ui/icons/Add';
import CollapseIcon from '@material-ui/icons/Remove';
import FileIcon from '@material-ui/icons/Description';
import Typography from '@material-ui/core/Typography';

const styles = makeStyles(theme => ({
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

function ItemTreeView(props) {

	const {item, action} = props;
	
	const classes = styles();
	
	if (item.childrens.length === 0) {
		return (
			<TreeItem onClick={ () => { action(item) } }
				key={item.id}
				nodeId={item.id}
				label={
					<div className={classes.labelRoot}>
					  <FileIcon color="inherit" className={classes.labelIcon} />
					  <Typography variant="body2" className={classes.labelText} >
						{item.name}
					  </Typography>
					</div>
				}
			/>
		)
	} else {
		return (
			<TreeItem 
				key={item.id}
				nodeId={item.id}
				label={
					<div className={classes.labelRoot}>
					  <PackageIcon color="inherit" className={classes.labelIcon} />
					  <Typography variant="body2" className={classes.labelText} >
						{item.name}
					  </Typography>
					</div>
				}
				children={item.childrens.map( 
					(children) => 
					(<ItemTreeView classes={classes} key={children.id} item={children} action={action}/>) 
				)}
			/>
		)
	}

}
	
function PackageTreeView(props) {

	const {items, action} = props;
	const classes = styles();

	return (			
		<TreeView
			defaultExpandIcon={<ExpandIcon className={classes.labelIcon} />}
			defaultCollapseIcon={<CollapseIcon className={classes.labelIcon} />} >
		  
			{ items.map( 
				(item) => 
				(<ItemTreeView nodeId={item.id} key={item.id} item={item} action={action}/> )
			)}
		  
		</TreeView>
	)
	
}
		
class PackageExplorer extends React.Component {
	
	structure = (correction) => {
		let data = { 'show': false };
		
		if (correction != null) {
			data.show = true;
			
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
			
			data.folders = folders.childrens;
		
		}
		
		return data;
	}
	
	shouldComponentUpdate(nextProps, nextState) {
		return (nextProps.correction !== this.props.correction);
	}
	
	render() {
		var data = this.structure(this.props.correction);

		if (data.show)
			return (
				<PackageTreeView items={data.folders} action={this.props.action} />
			)
		else
			return null;
	}
	
}

export default PackageExplorer;