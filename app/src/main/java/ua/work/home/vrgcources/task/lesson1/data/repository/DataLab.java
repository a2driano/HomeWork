package ua.work.home.vrgcources.task.lesson1.data.repository;

import java.util.ArrayList;
import java.util.List;

import ua.work.home.vrgcources.task.lesson1.data.model.EntryModel;

/**
 * Created by Andrii Papai on 07.10.2017.
 * <p>
 * Temp data mock
 */

public class DataLab {
    private List<EntryModel> mDataList;

    public DataLab() {
        mDataList = new ArrayList<>();
        initializeData();
    }

    /**
     * Get data
     */
    public List<EntryModel> getDataList() {
        return mDataList;
    }

    /**
     * add new Entry
     */
    public void addData(EntryModel model) {
        mDataList.add(model);
    }

    /**
     * Update exist Data
     */
    public void updateData(EntryModel model, int position) {
        EntryModel entryModel = mDataList.get(position);

        entryModel.setHeader(model.getHeader());
        entryModel.setDescription(model.getDescription());
    }

    /**
     * Get Entry from position
     */
    public EntryModel getEntry(int position) {
        return mDataList.get(position);
    }

    public void changePosition(int fromPosition, int toPosition) {
        EntryModel from = mDataList.get(fromPosition);
        EntryModel to = mDataList.get(toPosition);

        mDataList.get(toPosition).setHeader(from.getHeader()).setDescription(from.getDescription());
        mDataList.get(fromPosition).setHeader(to.getHeader()).setDescription(to.getDescription());
    }

    public void deleteView(int position) {
        mDataList.remove(position);
    }

    private void initializeData() {
        mDataList.add(new EntryModel("Windhelm", "Windhelm, also known as the City of Kings, is a city located in northeastern Skyrim. It serves as the capital of Eastmarch Hold. It is also the oldest city in Skyrim, possibly the oldest city of man on Tamriel that is still standing, dating back to the Merethic Era."));
        mDataList.add(new EntryModel("Riften", "Riften, referred to as Rifton in earlier records, is a city situated in the southeastern corner of The Rift, at the eastern end of Lake Honrich, with a good portion of the city actually spilling over the water atop large wooden piers. It is the southernmost and easternmost of all the cities in Skyrim. "));
        mDataList.add(new EntryModel("Markarth", "Markarth, also known as Markarth Side, is one of the major cities in Skyrim. It is known for its rich silver mines, which are mostly owned by the influential Silver-Bloods family. The cutthroat intrigue surrounding Markarth's silver producing industry popularized the local saying, Blood and silver are what flows through Markarth...."));
        mDataList.add(new EntryModel("Whiterun", "In Whiterun, Nords live in the 'traditional' manner: their lives are simple, harsh, and rooted in ancient traditions. Even the city's fortifications—wooden and stone palisade walls and the sheer defensive advantage offered by its position on a large bluff that raises the city above the surrounding tundra—are archaic by contemporary standards."));
        mDataList.add(new EntryModel("Solitude", "Solitude is the capital city of both Haafingar Hold, and all of Skyrim. Currently ruled by Jarl Elisif the Fair, widow of the late High King Torygg, it is the headquarters of the Imperial Legion stationed in Skyrim. General Tullius commands the Imperial army from the stone-walled Castle Dour, while Solitude's Jarl, Elisif, resides in the Blue Palace. The Thalmor have an embassy hidden in the nearby mountains. "));
        mDataList.add(new EntryModel("Dawnstar", "Dawnstar is one of the major cities located in Skyrim. It is the capital of The Pale, and is known for its rich mines and harbor. It also serves as a garrison town along the northern coast of Skyrim."));
        mDataList.add(new EntryModel("Morthal", "Morthal, said to be named after the great Atmoran hero Morihaus,[1] lies deep in the marsh, a foreboding area of Hjaalmarch isolated from other villages and shrouded by a constant and ominous fog."));
        mDataList.add(new EntryModel("Winterhold", "Winterhold is a former major city which is located in northern Skyrim. It is the capital city of the hold of the same name. Once a great capital rivaling Solitude in power and importance, Winterhold is now little more than a shell of its former self due to the Great Collapse."));
        mDataList.add(new EntryModel("Anvil", "City of Dibella on the Gold Coast in the southwest."));
        mDataList.add(new EntryModel("Bravil", "City of Mara by Niben Bay, south of the Imperial City."));
        mDataList.add(new EntryModel("Bruma", "City of Talos in the Jerall Mountains to the north, with a mainly Nord population."));
        mDataList.add(new EntryModel("Cheydinhal", "City of Arkay at the Valus Mountains in the northeast, with a mainly Dunmer population."));
        mDataList.add(new EntryModel("Chorrol", "City of Stendarr at the Colovian Highlands in the west."));
        mDataList.add(new EntryModel("Kvatch", "City of Akatosh overrun by the Daedra from the first portal to Oblivion, in the southwest."));
        mDataList.add(new EntryModel("Leyawiin", "City of Zenithar in Blackwood far south, with a mainly Khajiit and Argonian population."));
        mDataList.add(new EntryModel("Skingrad", "City of Julianos in the West Weald region."));
//        mDataList.add(new EntryModel("Windhelm", ""));
    }
}
