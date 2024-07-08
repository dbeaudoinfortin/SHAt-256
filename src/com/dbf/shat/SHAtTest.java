package com.dbf.shat;

import java.security.MessageDigest;
import com.dbf.shat.procedural.SHA2;
import com.dbf.shat.tree.SHA256Tree;
import com.dbf.shat.util.Utils;


public class SHAtTest {	
	public static void main(String [] args) {
		System.out.println("Start of SHA-256 Hash comparision.");
			
		try {
			System.out.println("Building message digests.");
			MessageDigest javaSHA256 = MessageDigest.getInstance("SHA-256");
			SHA2 sha256Procedural = new SHA2();
			SHA256Tree sha256Tree = new SHA256Tree();
			
			System.out.println("Short string hash:");
			System.out.println("Java MessageDigest -> " + Utils.bytesToHexString(javaSHA256.digest(SHORT_STRING.getBytes())));
			System.out.println("DBF SHA-256        -> " + Utils.bytesToHexString(sha256Procedural.digest(SHORT_STRING.getBytes())));
			System.out.println("DBF SHAt-256       -> " + Utils.bytesToHexString(sha256Tree.digest(SHORT_STRING.getBytes())));
			System.out.println("");
			
			System.out.println("Reseting digests.");
			javaSHA256.reset();
			sha256Procedural.reset();
			sha256Tree.reset();
			
			System.out.println("Long string hash:");
			System.out.println("Java MessageDigest -> " + Utils.bytesToHexString(javaSHA256.digest(LONG_STRING.getBytes())));
			System.out.println("DBF SHA-256        -> " + Utils.bytesToHexString(sha256Procedural.digest(LONG_STRING.getBytes())));
			System.out.println("DBF SHAt-256       -> " + Utils.bytesToHexString(sha256Tree.digest(LONG_STRING.getBytes())));
			System.out.println("");
		}
		catch (Throwable t) {
			System.out.println("FATAL EXCEPTION:");
			t.printStackTrace();
		}
		
		System.out.println("Comparision complete.");
	}
	
	//Bury this nonsense at the bottom
	private static final String SHORT_STRING = "SomeKindOfPasswordString";
	private static final String LONG_STRING = "BY THE NEXT MORNING, November 18, I was fully recovered from my exhaustion of the day before, and I climbed onto the platform just as the Nautilus’s chief officer was pronouncing his daily phrase. It then occurred to me that these words either referred to the state of the sea, or that they meant: “There’s nothing in sight.”\r\n"
		+ "\r\n"
		+ "And in truth, the ocean was deserted. Not a sail on the horizon. The tips of Crespo Island had disappeared during the night. The sea, absorbing every color of the prism except its blue rays, reflected the latter in every direction and sported a wonderful indigo tint. The undulating waves regularly took on the appearance of watered silk with wide stripes.\r\n"
		+ "\r\n"
		+ "I was marveling at this magnificent ocean view when Captain Nemo appeared. He didn’t seem to notice my presence and began a series of astronomical observations. Then, his operations finished, he went and leaned his elbows on the beacon housing, his eyes straying over the surface of the ocean.\r\n"
		+ "\r\n"
		+ "Meanwhile some twenty of the Nautilus’s sailors—all energetic, well-built fellows—climbed onto the platform. They had come to pull up the nets left in our wake during the night. These seamen obviously belonged to different nationalities, although indications of European physical traits could be seen in them all. If I’m not mistaken, I recognized some Irishmen, some Frenchmen, a few Slavs, and a native of either Greece or Crete. Even so, these men were frugal of speech and used among themselves only that bizarre dialect whose origin I couldn’t even guess. So I had to give up any notions of questioning them.\r\n"
		+ "\r\n"
		+ "The nets were hauled on board. They were a breed of trawl resembling those used off the Normandy coast, huge pouches held half open by a floating pole and a chain laced through the lower meshes. Trailing in this way from these iron glove makers, the resulting receptacles scoured the ocean floor and collected every marine exhibit in their path. That day they gathered up some unusual specimens from these fish-filled waterways: anglerfish whose comical movements qualify them for the epithet “clowns,” black Commerson anglers equipped with their antennas, undulating triggerfish encircled by little red bands, bloated puffers whose venom is extremely insidious, some olive-hued lampreys, snipefish covered with silver scales, cutlass fish whose electrocuting power equals that of the electric eel and the electric ray, scaly featherbacks with brown crosswise bands, greenish codfish, several varieties of goby, etc.; finally, some fish of larger proportions: a one-meter jack with a prominent head, several fine bonito from the genus Scomber decked out in the colors blue and silver, and three magnificent tuna whose high speeds couldn’t save them from our trawl.\r\n"
		+ "\r\n"
		+ "I estimate that this cast of the net brought in more than 1,000 pounds of fish. It was a fine catch but not surprising. In essence, these nets stayed in our wake for several hours, incarcerating an entire aquatic world in prisons made of thread. So we were never lacking in provisions of the highest quality, which the Nautilus’s speed and the allure of its electric light could continually replenish.\r\n"
		+ "\r\n"
		+ "These various exhibits from the sea were immediately lowered down the hatch in the direction of the storage lockers, some to be eaten fresh, others to be preserved.\r\n"
		+ "\r\n"
		+ "After its fishing was finished and its air supply renewed, I thought the Nautilus would resume its underwater excursion, and I was getting ready to return to my stateroom, when Captain Nemo turned to me and said without further preamble:\r\n"
		+ "\r\n"
		+ "“Look at this ocean, professor! Doesn’t it have the actual gift of life? Doesn’t it experience both anger and affection? Last evening it went to sleep just as we did, and there it is, waking up after a peaceful night!”\r\n"
		+ "\r\n"
		+ "No hellos or good mornings for this gent! You would have thought this eccentric individual was simply continuing a conversation we’d already started!\r\n"
		+ "\r\n"
		+ "“See!” he went on. “It’s waking up under the sun’s caresses! It’s going to relive its daily existence! What a fascinating field of study lies in watching the play of its organism. It owns a pulse and arteries, it has spasms, and I side with the scholarly Commander Maury, who discovered that it has a circulation as real as the circulation of blood in animals.”\r\n"
		+ "\r\n"
		+ "I’m sure that Captain Nemo expected no replies from me, and it seemed pointless to pitch in with “Ah yes,” “Exactly,” or “How right you are!” Rather, he was simply talking to himself, with long pauses between sentences. He was meditating out loud.\r\n"
		+ "\r\n"
		+ "“Yes,” he said, “the ocean owns a genuine circulation, and to start it going, the Creator of All Things has only to increase its heat, salt, and microscopic animal life. In essence, heat creates the different densities that lead to currents and countercurrents. Evaporation, which is nil in the High Arctic regions and very active in equatorial zones, brings about a constant interchange of tropical and polar waters. What’s more, I’ve detected those falling and rising currents that make up the ocean’s true breathing. I’ve seen a molecule of salt water heat up at the surface, sink into the depths, reach maximum density at -2 degrees centigrade, then cool off, grow lighter, and rise again. At the poles you’ll see the consequences of this phenomenon, and through this law of farseeing nature, you’ll understand why water can freeze only at the surface!”\r\n"
		+ "\r\n"
		+ "As the captain was finishing his sentence, I said to myself: “The pole! Is this brazen individual claiming he’ll take us even to that location?”\r\n"
		+ "\r\n"
		+ "Meanwhile the captain fell silent and stared at the element he had studied so thoroughly and unceasingly. Then, going on:\r\n"
		+ "\r\n"
		+ "“Salts,” he said, “fill the sea in considerable quantities, professor, and if you removed all its dissolved saline content, you’d create a mass measuring 4,500,000 cubic leagues, which if it were spread all over the globe, would form a layer more than ten meters high. And don’t think that the presence of these salts is due merely to some whim of nature. No. They make ocean water less open to evaporation and prevent winds from carrying off excessive amounts of steam, which, when condensing, would submerge the temperate zones. Salts play a leading role, the role of stabilizer for the general ecology of the globe!”";
}
