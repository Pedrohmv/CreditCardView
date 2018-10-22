package com.cooltechworks.creditcarddesign;

/**
 * Created by Harish on 01/01/16.
 */
public class CardSelector {

    public static final CardSelector VISA = new CardSelector(R.drawable.card_color_round_rect_purple, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_visa_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector ELO = new CardSelector(R.drawable.card_color_round_rect_purple, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_elo_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector HIPERCARD = new CardSelector(R.drawable.card_color_round_rect_purple, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_hipercard_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector SOROCRED = new CardSelector(R.drawable.card_color_round_rect_purple, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_sorocred_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector FORTBRASIL = new CardSelector(R.drawable.card_color_round_rect_yellow, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_fortbrasil_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector ASSOMISE = new CardSelector(R.drawable.card_color_round_rect_default, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_assomise_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector CREDISHOP = new CardSelector(R.drawable.card_color_round_rect_gray, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, R.drawable.ic_billing_credishop_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector MASTER = new CardSelector(R.drawable.card_color_round_rect_pink, R.drawable.chip_yellow, R.drawable.chip_yellow_inner, android.R.color.transparent, R.drawable.ic_billing_mastercard_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector AMEX = new CardSelector(R.drawable.card_color_round_rect_green, android.R.color.transparent, android.R.color.transparent, R.drawable.img_amex_center_face, R.drawable.ic_billing_amex_logo1, CardSelector.CVV_LENGHT_AMEX);
    public static final CardSelector DINERS = new CardSelector(R.drawable.card_color_round_rect_brown, android.R.color.transparent, android.R.color.transparent, android.R.color.transparent, R.drawable.ic_billing_diners_logo, CardSelector.CVV_LENGHT_DEFAULT);
    public static final CardSelector DEFAULT = new CardSelector(R.drawable.card_color_round_rect_default, R.drawable.chip, R.drawable.chip_inner, android.R.color.transparent, android.R.color.transparent, CardSelector.CVV_LENGHT_DEFAULT);

    public static final int CVV_LENGHT_DEFAULT = 3;
    public static final int CVV_LENGHT_AMEX = 4;


    private int mResCardId;
    private int mResChipOuterId;
    private int mResChipInnerId;
    private int mResCenterImageId;
    private int mResLogoId;
    private int mCvvLength = CVV_LENGHT_DEFAULT;

    public CardSelector(int mDrawableCard, int mDrawableChipOuter, int mDrawableChipInner, int mDrawableCenterImage, int logoId, int cvvLength) {
        this.mResCardId = mDrawableCard;
        this.mResChipOuterId = mDrawableChipOuter;
        this.mResChipInnerId = mDrawableChipInner;
        this.mResCenterImageId = mDrawableCenterImage;
        this.mResLogoId = logoId;
        this.mCvvLength = cvvLength;
    }

    public int getResCardId() {
        return mResCardId;
    }

    public void setResCardId(int mResCardId) {
        this.mResCardId = mResCardId;
    }

    public int getResChipOuterId() {
        return mResChipOuterId;
    }

    public void setResChipOuterId(int mResChipOuterId) {
        this.mResChipOuterId = mResChipOuterId;
    }

    public int getResChipInnerId() {
        return mResChipInnerId;
    }

    public void setResChipInnerId(int mResChipInnerId) {
        this.mResChipInnerId = mResChipInnerId;
    }

    public int getResCenterImageId() {
        return mResCenterImageId;
    }

    public void setResCenterImageId(int mResCenterImageId) {
        this.mResCenterImageId = mResCenterImageId;
    }

    public int getResLogoId() {
        return mResLogoId;
    }

    public void setResLogoId(int mResLogoId) {
        this.mResLogoId = mResLogoId;
    }

    public int getCvvLength() {
        return mCvvLength;
    }

    public void setCvvLength(int mCvvLength) {
        this.mCvvLength = mCvvLength;
    }

    public static CardSelector selectCardType(CreditCardUtils.CardType cardType) {
        switch(cardType) {
            case AMEX_CARD:
                return AMEX;
            case DINERS_CARD:
                return DINERS;
            case MASTER_CARD:
                return MASTER;
            case VISA_CARD:
                return VISA;
            case ELO_CARD:
                return ELO;
            case ASSOMISE:
                return ASSOMISE;
            case SOROCRED:
                return SOROCRED;
            case CREDISHOP:
                return CREDISHOP;
            case HIPERCARD:
                return HIPERCARD;
            case BANESECARD:
            case FORTBRASIL:
                return FORTBRASIL;
            default:
                return DEFAULT;
        }
    }

    public static CardSelector selectCard(String cardNumber) {
        if (cardNumber != null && cardNumber.length() >= 6) {
            CreditCardUtils.CardType cardType = CreditCardUtils.selectCardType(cardNumber.substring(0,6));
            CardSelector selector = selectCardType(cardType);

            if ((selector != DEFAULT) && (cardNumber.length() >= 3)) {
                int[] drawables = {R.drawable.card_color_round_rect_brown, R.drawable.card_color_round_rect_green, R.drawable.card_color_round_rect_pink, R.drawable.card_color_round_rect_purple, R.drawable.card_color_round_rect_blue};
                int hash = cardNumber.substring(0, 3).hashCode();

                if (hash < 0) {
                    hash = hash * -1;
                }

                int index = hash % drawables.length;

                int chipIndex = hash % 3;
                int[] chipOuter = {R.drawable.chip, R.drawable.chip_yellow, android.R.color.transparent};
                int[] chipInner = {R.drawable.chip_inner, R.drawable.chip_yellow_inner, android.R.color.transparent};

                selector.setResCardId(drawables[index]);
                selector.setResChipOuterId(chipOuter[chipIndex]);
                selector.setResChipInnerId(chipInner[chipIndex]);

                return selector;
            }
        }

        return DEFAULT;
    }
}
