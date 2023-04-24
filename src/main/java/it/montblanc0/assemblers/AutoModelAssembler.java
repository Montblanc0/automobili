package it.montblanc0.assemblers;

import it.montblanc0.entities.Auto;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelAssembler;

import java.util.ArrayList;
import java.util.List;

// @UNUSED
public class AutoModelAssembler implements RepresentationModelAssembler<Auto, EntityModel<Auto>> {

	private final EntityLinks entityLinks;


	public AutoModelAssembler(EntityLinks entityLinks) {
		this.entityLinks = entityLinks;
	}

	@Override
	public EntityModel<Auto> toModel(Auto entity) {
		return EntityModel.of(entity)
				.add(entityLinks.linkToItemResource(Auto.class, entity.getId())
						.withSelfRel()
				);
	}

	@Override
	public CollectionModel<EntityModel<Auto>> toCollectionModel(Iterable<? extends Auto> entities) {
		List<EntityModel<Auto>> autos = new ArrayList<>();
		for (Auto a : entities) {
			autos.add(this.toModel(a));
		}
		return CollectionModel.of(autos);
	}
}