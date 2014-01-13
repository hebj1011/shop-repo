package shop.Artikelverwaltung.domain;

import static shop.Artikelverwaltung.domain.AbstractArtikel.FAHRRAD;
import static javax.persistence.FetchType.EAGER;

import javax.persistence.Cacheable;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;
import javax.xml.bind.annotation.XmlRootElement;

import java.util.Set;

/**
 * @author <a href="mailto:hebj1011@HS-Karlsruhe.de">Bjoern Hetzel</a>
 */

@Entity
@Cacheable
@DiscriminatorValue(FAHRRAD)
@XmlRootElement
public class Fahrrad extends AbstractArtikel {

	private static final long serialVersionUID = 7087800972131210358L;
	
	@ElementCollection(fetch = EAGER)
	@CollectionTable(name = "artikel_farbe",
	                 joinColumns = @JoinColumn(name = "artikel_fk", nullable = false),
	                 uniqueConstraints =  @UniqueConstraint(columnNames = { "artikel_fk", "farbe" }),
	                 indexes = @Index(columnList = "artikel_fk"))
	@Column(table = "artikel_farbe", name = "farbe", length = 1, nullable = false)
	private Set<Farbe> farben;
	
	@Override
	public String toString() {
		return "Fahrrad [" + super.toString() + ", Farbe:" + farben + "]";
	}
}
