import React from 'react';
import SyntaxHighlighter from 'react-syntax-highlighter';
import { docco } from 'react-syntax-highlighter/dist/esm/styles/hljs';

class SourceFile extends React.Component {
			
	render() {	
		if (this.props.sourceFile != null) {
			return (
				<div style={{'textAlign': 'left'}}>
					{ this.props.sourceFile.name }
					<SyntaxHighlighter language={this.props.language} style={docco}>
					  { this.props.sourceFile.source.replace(/\t/g,"   ") }
					</SyntaxHighlighter>
				</div>
			)
		} else {
			return (<div> Nada seleccionado </div>);
		}		
	}
	
}

export default SourceFile;

