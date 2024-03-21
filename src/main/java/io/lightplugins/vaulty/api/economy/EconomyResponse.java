package io.lightplugins.vaulty.api.economy;

/**
 * @param amount       Amount modified by calling method
 * @param balance      New balance of account
 * @param type         Success or failure of call. Using Enum of ResponseType to determine valid
 *                     outcomes
 * @param errorMessage Error message if the variable 'type' is ResponseType.FAILURE
 */
public record EconomyResponse(double amount, double balance,
                              EconomyResponse.ResponseType type, String errorMessage) {
    /**
     * Enum for types of Responses indicating the status of a method call.
     */
    public static enum ResponseType {
        SUCCESS(1),
        FAILURE(2),
        NOT_IMPLEMENTED(3);

        private int id;

        ResponseType(int id) {
            this.id = id;
        }

        int getId() {
            return id;
        }
    }

    /**
     * Constructor for EconomyResponse
     *
     * @param amount       Amount modified during operation
     * @param balance      New balance of account
     * @param type         Success or failure type of the operation
     * @param errorMessage Error message if necessary (commonly null)
     */
    public EconomyResponse {
    }

    /**
     * Checks if an operation was successful
     *
     * @return Value
     */
    public boolean transactionSuccess() {
        return type == ResponseType.SUCCESS;
    }

}
