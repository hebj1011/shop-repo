package shop.Kundenverwaltung.domain;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * @author <a href="mailto:lade1011@HS-Karlsruhe.de">Denis Langer</a>
 */
@Converter(autoApply = true)
public class FamilienstandTypeConverter implements AttributeConverter<FamilienstandType, String> {
	@Override
	public String convertToDatabaseColumn(FamilienstandType familienstandType) {
		if (familienstandType == null) {
			return null;
		}
		return familienstandType.getInternal();
	}

	@Override
	public FamilienstandType convertToEntityAttribute(String internal) {
		return FamilienstandType.build(internal);
	}
}
