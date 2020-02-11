import React from 'react';
import TreeView from "@material-ui/lab/TreeView";
import TreeItem from "@material-ui/lab/TreeItem";
import PackageIcon from '@material-ui/icons/BorderAll';
import ExpandIcon from '@material-ui/icons/Add';
import CollapseIcon from '@material-ui/icons/Remove';
import FileIcon from '@material-ui/icons/Description';
import Typography from '@material-ui/core/Typography';
import Style from '../utils/style';
import ExplorerUtils from './explorer-utils.js'

function ItemTreeView(props) {

	const {item, action} = props;
	
	const style = Style();
	
	if (item.childrens.length === 0) {
		return (
			<TreeItem onClick={ () => { action(item) } }
				key={item.id}
				nodeId={item.id}
				label={
					<div className={style.labelRoot}>
					  <FileIcon color="inherit" className={style.labelIcon} />
					  <Typography variant="body2" className={style.sourceCode} >
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
					<div className={style.labelRoot}>
					  <PackageIcon color="inherit" className={style.labelIcon} />
					  <Typography variant="body2" className={style.sourceCode} >
						{item.name}
					  </Typography>
					</div>
				}
				children={item.childrens.map( 
					(children) => 
					(<ItemTreeView style={style} key={children.id} item={children} action={action}/>) 
				)}
			/>
		)
	}

}
	
function PackageTreeView(props) {

	const {items, action} = props;
	const style = Style();

	return (			
		<TreeView
			defaultExpandIcon={<ExpandIcon className={style.labelIcon} />}
			defaultCollapseIcon={<CollapseIcon className={style.labelIcon} />} >
		  
			{ items.map( 
				(item) => 
				( <ItemTreeView nodeId={item.id} key={item.id} item={item} action={action} /> )
			)}
		  
		</TreeView>
	)
	
}
		
class PackageExplorer extends React.Component {
	
	shouldComponentUpdate(nextProps, nextState) {
		return (nextProps.correction !== this.props.correction);
	}
	
	render() {
		if (this.props.correction !== null) {
			var data = ExplorerUtils.structureCorrection(this.props.correction);
			return ( <PackageTreeView items={data} action={this.props.action} /> )
		}
		else
			return null;
	}
	
}

export default PackageExplorer;