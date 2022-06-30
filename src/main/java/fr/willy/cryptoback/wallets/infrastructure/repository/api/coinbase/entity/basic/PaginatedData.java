package fr.willy.cryptoback.wallets.infrastructure.repository.api.coinbase.entity.basic;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

@Getter
@Accessors(fluent = true)
@NoArgsConstructor
@AllArgsConstructor
public class PaginatedData<T> {

    private PaginationEntity pagination;
    private List<T> data;
}
