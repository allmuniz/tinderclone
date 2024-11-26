package project.allmuniz.tinderclone.configs;

import org.springframework.amqp.support.converter.DefaultJackson2JavaTypeMapper;
import org.springframework.amqp.support.converter.Jackson2JavaTypeMapper;

public class AllowedClassMapper extends DefaultJackson2JavaTypeMapper {

    public AllowedClassMapper() {
        setTrustedPackages("project.allmuniz.relationshipapp.entities"); // Permite pacotes específicos
    }

    @Override
    public Jackson2JavaTypeMapper.TypePrecedence getTypePrecedence() {
        return Jackson2JavaTypeMapper.TypePrecedence.TYPE_ID; // Usa o identificador de tipo
    }
}
