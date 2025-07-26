package br.edu.utfpr.dungeontable.service;


import br.edu.utfpr.dungeontable.exception.BusinessException;
import br.edu.utfpr.dungeontable.exception.ErrorCode;
import br.edu.utfpr.dungeontable.model.tools.Magic;
import br.edu.utfpr.dungeontable.repository.MagicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MagicService {

    @Autowired
    private MagicRepository magicRepository;

    @Transactional(propagation = Propagation.REQUIRED)
    public Magic save(Magic magic) {
        if (magic.getName() == null || magic.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name item");
        } else if (magic.getSchoolMagic() == null || magic.getSchoolMagic().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "school magic");
        } else if (magic.getLevel() == null || magic.getLevel().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "level magic");
        } else if (magic.getComponents() == null || magic.getComponents().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "components");
        } else if (magic.getCastingTime() == null || magic.getCastingTime().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "castingTime");
        } else if (magic.getRange() == null || magic.getRange().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "Range");
        } else if (magic.getDuration() == null || magic.getDuration().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "Duration");
        }
        return magicRepository.save(magic);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Magic update(Magic magic) {
        if(magic.getId() == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }else if (magic.getName() == null || magic.getName().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "name item");
        } else if (magic.getSchoolMagic() == null || magic.getSchoolMagic().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "school magic");
        } else if (magic.getLevel() == null || magic.getLevel().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "level magic");
        } else if (magic.getComponents() == null || magic.getComponents().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "components");
        } else if (magic.getCastingTime() == null || magic.getCastingTime().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "castingTime");
        } else if (magic.getRange() == null || magic.getRange().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "Range");
        } else if (magic.getDuration() == null || magic.getDuration().isEmpty()) {
            throw new BusinessException(ErrorCode.ATTRIBUTE_REQUIRED, "Duration");
        }
        return magicRepository.save(magic);
    }

    public Magic findById(Long id) {
        return magicRepository.findById(id).orElse(null);
    }

    public List<Magic> findAll() {
        return magicRepository.findAll();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Long id) {
        if(id == null){
            throw new BusinessException(ErrorCode.ID_REQUIRED);
        }
        magicRepository.deleteById(id);
    }
}
