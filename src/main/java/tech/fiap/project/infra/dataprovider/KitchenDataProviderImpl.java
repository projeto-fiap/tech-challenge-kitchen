package tech.fiap.project.infra.dataprovider;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.fiap.project.domain.dataprovider.KitchenDataProvider;
import tech.fiap.project.domain.entity.Kitchen;
import tech.fiap.project.infra.entity.KitchenEntity;
import tech.fiap.project.infra.mapper.KitchenRepositoryMapper;
import tech.fiap.project.infra.repository.KitchenRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class KitchenDataProviderImpl implements KitchenDataProvider {

	private KitchenRepository kitchenRepository;

	@Override
	public List<Kitchen> retrieveAll() {
		return KitchenRepositoryMapper.toDomain(kitchenRepository.findAll());
	}

	@Override
	public List<Kitchen> retrieveAllIds(List<Long> orderIds) {
		return KitchenRepositoryMapper.toDomain(kitchenRepository.findAllById(orderIds));
	}

	@Override
	public Kitchen create(Kitchen kitchen) {
		return saveRepository(kitchen);
	}

	@Override
	public Kitchen update(Kitchen kitchen) {
		return saveRepository(kitchen);
	}

	private Kitchen saveRepository(Kitchen kitchen) {
		KitchenEntity entity = KitchenRepositoryMapper.toEntity(kitchen);
		KitchenEntity kitchenSaved = kitchenRepository.save(entity);
		return KitchenRepositoryMapper.toDomain(kitchenSaved);
	}

	@Override
	public Optional<Kitchen> retrieveById(Long orderId) {
		return kitchenRepository.findById(orderId).map(KitchenRepositoryMapper::toDomain);
	}

}
