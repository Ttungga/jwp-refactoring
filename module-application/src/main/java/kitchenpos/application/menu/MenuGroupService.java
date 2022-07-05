package kitchenpos.application.menu;

import java.util.List;
import kitchenpos.common.menu.domain.MenuGroup;
import kitchenpos.common.menu.repository.MenuGroupRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MenuGroupService {
    private final MenuGroupRepository menuGroupRepository;

    public MenuGroupService(MenuGroupRepository menuGroupRepository) {
        this.menuGroupRepository = menuGroupRepository;
    }

    @Transactional
    public MenuGroup create(final MenuGroup menuGroup) {
        return menuGroupRepository.save(menuGroup);
    }

    @Transactional(readOnly = true)
    public List<MenuGroup> list() {
        return menuGroupRepository.findAll();
    }

    @Transactional(readOnly = true)
    public boolean existsById(final Long id) {
        return menuGroupRepository.existsById(id);
    }
}