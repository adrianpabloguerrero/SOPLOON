import React from 'react';
import VerticalList from '../utils/vertical-list';

class ExplorerVerticalList extends React.Component {
		
	constructor(props) {
		super(props);
		this.state = { items: []};
	}
	
	static getDerivedStateFromProps(props, state) {
		
		const {currentUser, users, currentProject, projects, currentCorrection, corrections} = props.data;
		const {selectUser, selectProject, selectCorrection} = props.actions;

		var newState = { title: '', items: [], show: false };

		if (currentCorrection == null) {
			newState.show = true;
			if (currentProject == null) {
				if (currentUser == null) {
					newState.title = 'Usuarios';
					users.forEach( user => { newState.items.push( {'text': user.name, 'action': () => selectUser(user) } )});
				} else {
					newState.title = 'Projectos';
					projects.forEach( project => { newState.items.push( {'text': project.name, 'action': () => selectProject(project) } )});
				}
			} else {
				newState.title = 'Correction';
				corrections.forEach( correction => { newState.items.push( {'text': new Date(correction.date).toLocaleString(), 'action': () => selectCorrection(correction) } )});
			}
		}
		
		return newState;
	}
	
	render() {
		if (this.state.show)
			return (<VerticalList title={this.state.title} items={this.state.items}/>)
		else
			return null;
	}
	
}

export default ExplorerVerticalList;

