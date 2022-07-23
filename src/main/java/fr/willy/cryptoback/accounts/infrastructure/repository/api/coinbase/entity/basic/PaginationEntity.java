package fr.willy.cryptoback.accounts.infrastructure.repository.api.coinbase.entity.basic;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationEntity {
    /**
     * A cursor for use in pagination. ending_before is an resource ID that defines your place in the list.
     */
    private String ending_before;


    /**
     * A cursor for use in pagination. starting_after is an resource ID that defines your place in the list.
     */
    private String starting_after;

    private String previous_ending_before;
    private String next_starting_after;

    /**
     * Number of results per call. Accepted values: 0 - 100. Default 25
     */
    private int limit;

    /**
     * Result order. Accepted values: desc (default), asc
     */
    private String order;

    /**
     * Url of the previous page
     */
    private String previous_uri;

    /**
     * Url of the next page
     */
    private String next_uri;
}
