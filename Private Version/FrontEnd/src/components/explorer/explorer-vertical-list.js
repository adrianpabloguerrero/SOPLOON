import React from 'react';
import VerticalList from '../utils/vertical-list';

class ExplorerVerticalList extends React.Component {
		
	shouldComponentUpdate(nextProps, nextState) {
		return (nextProps.data !== this.props.data);
	}
	
	structure(props) {
		
		const {currentUser, users, currentProject, projects, currentCorrection, corrections} = props.data;
		const {selectUser, selectProject, selectCorrection} = props.actions;

		var data = { title: '', items: [], show: false };

		if (currentCorrection == null) {
			data.show = true;
			if (currentProject == null) {
				if (currentUser == null) {
					data.title = 'Usuarios';
					users.forEach( user => { data.items.push( {'text': 'U' + user.id + ' - ' + user.name, 'action': () => selectUser(user) } )});
				} else {
					data.title = 'Projectos';
					projects.forEach( project => { data.items.push( {'text': 'P' + project.id + ' - ' + project.name, 'action': () => selectProject(project) } )});
				}
			} else {
				data.title = 'Correcciones';
				corrections.forEach( correction => { data.items.push( {'text': new Date(correction.date).toLocaleString(), 'action': () => selectCorrection(correction) } )});
			}
		}
		
		return data;
	}
	
	render() {
		var data = this.structure(this.props);
		if (data.show)
			return (<VerticalList title={data.title} loading={this.props.loading} items={data.items}/>)
		else
			return null;
	}
	
}

export default ExplorerVerticalList;

