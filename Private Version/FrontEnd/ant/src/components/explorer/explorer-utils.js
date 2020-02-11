
const ExplorerUtils = {};

ExplorerUtils.structureCorrection = (correction) => {
	
	if (correction != null) {
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
		
		return folders.childrens;
	
	}
	
}

export default ExplorerUtils;
