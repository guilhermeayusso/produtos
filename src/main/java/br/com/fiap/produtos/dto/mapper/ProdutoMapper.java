package br.com.fiap.produtos.dto.mapper;

import br.com.fiap.produtos.dto.ProdutoDto;
import br.com.fiap.produtos.entity.Produto;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ProdutoMapper {

    ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);

    Produto toEntity(ProdutoDto produtoDto);

    ProdutoDto toDto(Produto produto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Produto partialUpdate(ProdutoDto produtoDto, @MappingTarget Produto produto);
}