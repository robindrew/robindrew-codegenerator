package com.robindrew.codegenerator.model.object.id;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.robindrew.codegenerator.model.IModel;
import com.robindrew.codegenerator.model.object.IModelObject;

public class ModelIdRegistry {

	private static final Logger log = LoggerFactory.getLogger(ModelIdRegistry.class);

	private final Map<Integer, IModel> idToModelMap = new LinkedHashMap<>();
	private final Map<Class<? extends IModelObject>, Set<Integer>> duplicateMap = new LinkedHashMap<Class<? extends IModelObject>, Set<Integer>>();

	public void setIds(IModel model) {
		int baseId = model.getId();
		if (baseId < 0) {
			throw new IllegalStateException("negative id=" + baseId);
		}

		// Duplicate check
		IModel existing = idToModelMap.get(baseId);
		if (existing != null) {
			if (existing.equals(model)) {
				return;
			}
			throw new IllegalStateException("existing=" + existing.getPackage() + ", duplicate=" + model.getPackage());
		}
		idToModelMap.put(baseId, model);

		setObjectIds(baseId, model.getBeanList());
	}

	private synchronized void setObjectIds(int baseId, List<? extends IModelObject> objectList) {

		// First pass - known ids
		for (IModelObject object : objectList) {
			Class<? extends IModelObject> type = object.getClass();

			// We are only interested in ids that are already known
			int id = object.getId();
			if (id == 0) {
				continue;
			}

			setObjectId(object, type, id);
		}

		// Second pass - generated ids
		int localId = 0;
		for (IModelObject object : objectList) {
			Class<? extends IModelObject> type = object.getClass();

			int id = object.getId();

			// We are only interested in ids that are to be generated
			if (id != 0) {
				break;
			}

			// Make sure it is not a duplicate
			Set<Integer> duplicates = getDuplicates(type);
			while (true) {
				localId++;
				id = baseId + localId;
				if (duplicates.add(id)) {
					break;
				}
			}

			// Done
			log.info(object.getName() + "@" + type.getSimpleName() + " -> " + id);
			object.setId(id);
		}
	}

	private void setObjectId(IModelObject object, Class<? extends IModelObject> type, int id) {

		// Check if duplicate
		Set<Integer> duplicates = getDuplicates(type);
		if (!duplicates.add(id)) {
			throw new IllegalStateException("duplicate id " + id + " for type " + object.getClass() + " (" + object.getName() + ")");
		}

		// Done
		log.info(object.getName() + "@" + type.getSimpleName() + " -> " + id);
		object.setId(id);
	}

	private Set<Integer> getDuplicates(Class<? extends IModelObject> type) {
		Set<Integer> set = duplicateMap.get(type);
		if (set == null) {
			set = new HashSet<Integer>();
			duplicateMap.put(type, set);
		}
		return set;
	}
}
